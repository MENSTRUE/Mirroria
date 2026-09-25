package com.mirrora.app.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Flip
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.ui.components.FacialAreaRow
import com.mirrora.app.ui.components.ResultImagePreview
import com.mirrora.app.ui.components.ResultPreviewMode
import com.mirrora.app.ui.components.SectionHeader
import com.mirrora.app.ui.components.SymmetryScore
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraBlueLight
import com.mirrora.app.ui.theme.MirroraBorder
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun HasilAnalisisScreen(
    result: AnalysisResult,
    onBack: () -> Unit,
    onExplainAnalysis: () -> Unit
) {
    val context = LocalContext.current
    var previewMode by rememberSaveable { mutableStateOf(ResultPreviewMode.ORIGINAL) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MirroraBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = MirroraTextPrimary
                )
            }
            Text(
                text = "Hasil analisis",
                style = MaterialTheme.typography.titleMedium,
                color = MirroraTextPrimary,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    val shareText = buildString {
                        appendLine("Hasil analisis MIRRORA")
                        appendLine("Indeks simetri visual: ${result.symmetryPercent}%")
                        append(result.supportingText)
                    }
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    context.startActivity(Intent.createChooser(intent, "Bagikan hasil MIRRORA"))
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Bagikan",
                    tint = MirroraTextPrimary
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            ResultImagePreview(
                imageUri = result.imageUri,
                mode = previewMode,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            SymmetryScore(
                percent = result.symmetryPercent,
                supportingText = result.supportingText
            )
            TextButton(
                onClick = onExplainAnalysis,
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Analisis ini berdasarkan apa?",
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                val reflectionActive = previewMode == ResultPreviewMode.REFLECTION
                OutlinedButton(
                    onClick = {
                        previewMode = if (reflectionActive) ResultPreviewMode.ORIGINAL else ResultPreviewMode.REFLECTION
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (reflectionActive) MirroraBlueLight else Color.Transparent,
                        contentColor = MirroraPrimaryBlue
                    ),
                    border = BorderStroke(1.dp, if (reflectionActive) MirroraPrimaryBlue else MirroraBorder)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Flip,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (reflectionActive) "Asli" else "Refleksi",
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                val landmarkActive = previewMode == ResultPreviewMode.LANDMARK
                OutlinedButton(
                    onClick = {
                        previewMode = if (landmarkActive) ResultPreviewMode.ORIGINAL else ResultPreviewMode.LANDMARK
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (landmarkActive) MirroraBlueLight else Color.Transparent,
                        contentColor = MirroraPrimaryBlue
                    ),
                    border = BorderStroke(1.dp, if (landmarkActive) MirroraPrimaryBlue else MirroraBorder)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.GridOn,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (landmarkActive) "Asli" else "Landmark",
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            SectionHeader(title = "Detail area wajah")
            Spacer(modifier = Modifier.height(4.dp))
            result.facialAreas.forEach { area ->
                FacialAreaRow(type = area.type, percent = area.percent)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.padding(bottom = 24.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = MirroraTextSecondary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Indeks ini merupakan visualisasi perbandingan struktur wajah dan bukan ukuran kecantikan, kesehatan, atau kualitas seseorang.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MirroraTextSecondary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
