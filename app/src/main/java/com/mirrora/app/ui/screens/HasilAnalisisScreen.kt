package com.mirrora.app.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.ui.components.FacePlaceholder
import com.mirrora.app.ui.components.FacialAreaRow
import com.mirrora.app.ui.components.SectionHeader
import com.mirrora.app.ui.components.SymmetryScore
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraBorder
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun HasilAnalisisScreen(
    result: AnalysisResult,
    onBack: () -> Unit
) {
    val context = LocalContext.current

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
            IconButton(onClick = {
                Toast.makeText(context, "Bagikan segera hadir", Toast.LENGTH_SHORT).show()
            }) {
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                FacePlaceholder(
                    imageUri = result.imageUri,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "L",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
                Text(
                    text = "R",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
                )
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
            SymmetryScore(
                percent = result.symmetryPercent,
                supportingText = result.supportingText
            )

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = { Toast.makeText(context, "Refleksi segera hadir", Toast.LENGTH_SHORT).show() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MirroraPrimaryBlue),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MirroraBorder)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Flip,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Lihat refleksi",
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(start = 12.dp))
                OutlinedButton(
                    onClick = { Toast.makeText(context, "Landmark segera hadir", Toast.LENGTH_SHORT).show() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MirroraPrimaryBlue),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MirroraBorder)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.GridOn,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Landmark",
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 28.dp))
            SectionHeader(title = "Detail area wajah")
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 4.dp))
            result.facialAreas.forEach { area ->
                FacialAreaRow(type = area.type, percent = area.percent)
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
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