package com.mirrora.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun MirroraHeader(
    onSettingsClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MirroraPrimaryBlue)
        )
        Text(
            text = "  MIRRORA",
            color = MirroraTextPrimary,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(1f))
        if (onSettingsClick != null) {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "Pengaturan",
                    tint = MirroraTextSecondary
                )
            }
        }
    }
}
