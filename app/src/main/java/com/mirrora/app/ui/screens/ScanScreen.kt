package com.mirrora.app.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.mirrora.app.ui.components.CameraControls
import com.mirrora.app.ui.components.FacePlaceholder
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ScanScreen(
    onClose: () -> Unit,
    onPhotoReady: (Uri?) -> Unit
) {
    val context = LocalContext.current

    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
        )
    }
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_FRONT) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted -> hasCameraPermission = granted }

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> if (uri != null) onPhotoReady(uri) }

    // Held across recompositions so the shutter button can trigger a capture.
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        if (hasCameraPermission) {
            CameraPreview(
                lensFacing = lensFacing,
                onImageCaptureReady = { imageCapture = it }
            )
        } else {
            // Camera not available (permission not granted yet) -> neutral placeholder,
            // per design spec section 14.
            FacePlaceholder(
                shape = androidx.compose.ui.graphics.RectangleShape,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Oval face guide overlay.
        Canvas(modifier = Modifier.fillMaxSize()) {
            val ovalWidth = size.width * 0.62f
            val ovalHeight = size.height * 0.42f
            val topLeft = androidx.compose.ui.geometry.Offset(
                (size.width - ovalWidth) / 2f,
                (size.height - ovalHeight) / 2f
            )
            drawOval(
                color = Color.White.copy(alpha = 0.85f),
                topLeft = topLeft,
                size = Size(ovalWidth, ovalHeight),
                style = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 10f), 0f)
                )
            )
        }

        // Top bar.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Tutup",
                    tint = Color.White
                )
            }
        }

        // Instruction text.
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Posisikan wajah di dalam bingkai",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }

        // Bottom controls.
        CameraControls(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            onGalleryClick = {
                galleryLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            onShutterClick = {
                if (hasCameraPermission) {
                    capturePhoto(context, imageCapture, onPhotoReady)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            onSwitchCameraClick = {
                lensFacing = if (lensFacing == CameraSelector.LENS_FACING_FRONT) {
                    CameraSelector.LENS_FACING_BACK
                } else {
                    CameraSelector.LENS_FACING_FRONT
                }
            }
        )
    }
}

@Composable
private fun CameraPreview(
    lensFacing: Int,
    onImageCaptureReady: (ImageCapture) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }

    DisposableEffect(lensFacing) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            val imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
                onImageCaptureReady(imageCapture)
            } catch (_: Exception) {
                // Camera binding failed (e.g. emulator without a camera) -> preview stays blank,
                // the oval guide and placeholder styling still render correctly.
            }
        }, ContextCompat.getMainExecutor(context))

        onDispose {
            ProcessCameraProvider.getInstance(context).get().unbindAll()
        }
    }

    AndroidView(
        factory = { previewView },
        modifier = Modifier.fillMaxSize()
    )
}

private fun capturePhoto(
    context: android.content.Context,
    imageCapture: ImageCapture?,
    onPhotoReady: (Uri?) -> Unit
) {
    if (imageCapture == null) {
        onPhotoReady(null)
        return
    }
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(System.currentTimeMillis())
    val photoFile = File(context.cacheDir, "MIRRORA_$name.jpg")
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onPhotoReady(Uri.fromFile(photoFile))
            }

            override fun onError(exception: ImageCaptureException) {
                onPhotoReady(null)
            }
        }
    )
}
