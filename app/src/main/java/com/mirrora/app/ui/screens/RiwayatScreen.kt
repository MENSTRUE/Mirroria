package com.mirrora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.ui.components.AnalysisHistoryItem
import com.mirrora.app.ui.components.SearchField
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun RiwayatScreen(
    results: List<AnalysisResult>,
    onResultClick: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    val filtered = remember(results, query) {
        if (query.isBlank()) {
            results
        } else {
            results.filter {
                it.date.contains(query, ignoreCase = true) ||
                    it.time.contains(query, ignoreCase = true) ||
                    it.symmetryPercent.toString().contains(query)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MirroraBackground)
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Riwayat",
            style = MaterialTheme.typography.titleLarge,
            color = MirroraTextPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchField(
            value = query,
            onValueChange = { query = it },
            placeholder = "Cari riwayat analisis"
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "${filtered.size} hasil",
            style = MaterialTheme.typography.bodySmall,
            color = MirroraTextSecondary
        )

        Spacer(modifier = Modifier.height(4.dp))

        if (filtered.isEmpty()) {
            Text(
                text = if (results.isEmpty()) {
                    "Belum ada riwayat analisis."
                } else {
                    "Tidak ada hasil yang cocok."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(filtered, key = { it.id }) { result ->
                    AnalysisHistoryItem(
                        result = result,
                        onClick = { onResultClick(result.id) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
