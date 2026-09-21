package com.mirrora.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun AnalysisHistoryItem(
    result: AnalysisResult,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FacePlaceholder(
            imageUri = result.imageUri,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(52.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = result.date,
                style = MaterialTheme.typography.bodyMedium,
                color = MirroraTextPrimary
            )
            Text(
                text = result.time,
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary
            )
        }
        Text(
            text = "${result.symmetryPercent}%",
            style = MaterialTheme.typography.bodyLarge,
            color = MirroraPrimaryBlue
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MirroraTextSecondary,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
