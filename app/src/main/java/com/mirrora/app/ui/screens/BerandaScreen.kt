package com.mirrora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.ui.components.AnalysisHistoryItem
import com.mirrora.app.ui.components.MirroraHeader
import com.mirrora.app.ui.components.PrimaryAnalysisCard
import com.mirrora.app.ui.components.SectionHeader
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun BerandaScreen(
    recentResults: List<AnalysisResult>,
    onStartScan: () -> Unit,
    onSeeAllHistory: () -> Unit,
    onResultClick: (String) -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MirroraBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        MirroraHeader(onSettingsClick = onSettingsClick)

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Lihat wajahmu\ndari sisi yang berbeda.",
            style = MaterialTheme.typography.titleLarge,
            color = MirroraTextPrimary
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Visualisasikan keseimbangan wajahmu secara sederhana.",
            style = MaterialTheme.typography.bodyMedium,
            color = MirroraTextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))
        PrimaryAnalysisCard(onClick = onStartScan)

        Spacer(modifier = Modifier.height(30.dp))
        SectionHeader(
            title = "Analisis terbaru",
            actionLabel = if (recentResults.isNotEmpty()) "Lihat semua" else null,
            onActionClick = if (recentResults.isNotEmpty()) onSeeAllHistory else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (recentResults.isEmpty()) {
            Text(
                text = "Belum ada analisis. Mulai analisis pertamamu di atas.",
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            recentResults.take(3).forEach { result ->
                AnalysisHistoryItem(
                    result = result,
                    onClick = { onResultClick(result.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
