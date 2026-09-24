package com.mirrora.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextSecondary

enum class ResultPreviewMode {
    ORIGINAL,
    REFLECTION,
    LANDMARK
}

@Composable
fun ResultImagePreview(
    imageUri: String?,
    mode: ResultPreviewMode,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = modifier.background(Color(0xFFF1F3F6), shape),
        contentAlignment = Alignment.Center
    ) {
        FacePlaceholder(
            imageUri = imageUri,
            shape = shape,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = if (mode == ResultPreviewMode.REFLECTION) -1f else 1f
                }
        )

        when (mode) {
            ResultPreviewMode.ORIGINAL -> OriginalOverlay()
            ResultPreviewMode.REFLECTION -> ReflectionOverlay()
            ResultPreviewMode.LANDMARK -> LandmarkOverlay()
        }
    }
}

@Composable
private fun OriginalOverlay() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                color = Color.White.copy(alpha = 0.80f),
                start = Offset(size.width / 2f, 18.dp.toPx()),
                end = Offset(size.width / 2f, size.height - 18.dp.toPx()),
                strokeWidth = 1.5.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = "L",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
        Text(
            text = "R",
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

@Composable
private fun ReflectionOverlay() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Refleksi",
            style = MaterialTheme.typography.labelSmall,
            color = MirroraPrimaryBlue,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
                .background(
                    color = Color.White.copy(alpha = 0.92f),
                    shape = RoundedCornerShape(999.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun LandmarkOverlay() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val points = listOf(
                Offset(size.width * 0.34f, size.height * 0.34f),
                Offset(size.width * 0.43f, size.height * 0.33f),
                Offset(size.width * 0.57f, size.height * 0.33f),
                Offset(size.width * 0.66f, size.height * 0.34f),
                Offset(size.width * 0.50f, size.height * 0.43f),
                Offset(size.width * 0.45f, size.height * 0.52f),
                Offset(size.width * 0.55f, size.height * 0.52f),
                Offset(size.width * 0.50f, size.height * 0.59f),
                Offset(size.width * 0.38f, size.height * 0.65f),
                Offset(size.width * 0.50f, size.height * 0.70f),
                Offset(size.width * 0.62f, size.height * 0.65f)
            )

            val connections = listOf(
                0 to 1,
                2 to 3,
                1 to 4,
                2 to 4,
                4 to 5,
                4 to 6,
                5 to 7,
                6 to 7,
                7 to 8,
                7 to 9,
                7 to 10
            )

            connections.forEach { (a, b) ->
                drawLine(
                    color = MirroraPrimaryBlue.copy(alpha = 0.55f),
                    start = points[a],
                    end = points[b],
                    strokeWidth = 1.dp.toPx()
                )
            }

            points.forEach { point ->
                drawCircle(
                    color = Color.White,
                    radius = 4.dp.toPx(),
                    center = point
                )
                drawCircle(
                    color = MirroraPrimaryBlue,
                    radius = 4.dp.toPx(),
                    center = point,
                    style = Stroke(width = 1.5.dp.toPx())
                )
            }
        }

        Text(
            text = "Pratinjau landmark",
            style = MaterialTheme.typography.labelSmall,
            color = MirroraTextSecondary,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
                .background(
                    color = Color.White.copy(alpha = 0.92f),
                    shape = RoundedCornerShape(999.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}
