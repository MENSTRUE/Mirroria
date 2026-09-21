package com.mirrora.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mirrora.app.ui.theme.MirroraDanger
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun SettingsRow(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    trailingValue: String? = null,
    destructive: Boolean = false,
    showChevron: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val contentColor = if (destructive) MirroraDanger else MirroraTextPrimary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .let { if (onClick != null) it.clickable { onClick() } else it }
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = contentColor,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        )
        if (trailingValue != null) {
            Text(
                text = trailingValue,
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary
            )
        }
        if (showChevron && onClick != null) {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MirroraTextSecondary,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
