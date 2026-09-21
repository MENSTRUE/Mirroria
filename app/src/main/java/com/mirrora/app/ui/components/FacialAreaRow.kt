package com.mirrora.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mirrora.app.data.model.FacialAreaType
import com.mirrora.app.ui.theme.MirroraMutedBackground
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

private fun iconFor(type: FacialAreaType): ImageVector = when (type) {
    FacialAreaType.MATA -> Icons.Outlined.Visibility
    FacialAreaType.ALIS -> Icons.Outlined.Remove
    FacialAreaType.HIDUNG -> Icons.Outlined.Circle
    FacialAreaType.MULUT -> Icons.Outlined.EmojiEmotions
    FacialAreaType.RAHANG -> Icons.Outlined.Face
}

@Composable
fun FacialAreaRow(
    type: FacialAreaType,
    percent: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = iconFor(type),
            contentDescription = type.label,
            tint = MirroraTextSecondary,
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = type.label,
            style = MaterialTheme.typography.bodyMedium,
            color = MirroraTextPrimary,
            modifier = Modifier
                .padding(start = 12.dp)
                .width(64.dp)
        )
        LinearProgressIndicator(
            progress = { percent / 100f },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(6.dp),
            color = MirroraPrimaryBlue,
            trackColor = MirroraMutedBackground,
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        Text(
            text = "$percent%",
            style = MaterialTheme.typography.bodySmall,
            color = MirroraTextSecondary,
            textAlign = TextAlign.End,
            modifier = Modifier.width(40.dp)
        )
    }
}
