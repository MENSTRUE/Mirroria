package com.mirrora.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirrora.app.ui.theme.MirroraBorder
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraSurface
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun SymmetryScore(
    percent: Int,
    supportingText: String,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(18.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MirroraSurface, shape)
            .border(1.dp, MirroraBorder, shape)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Indeks simetri visual",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MirroraTextPrimary
                )
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Informasi",
                    tint = MirroraTextSecondary,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .size(16.dp)
                )
            }

            Text(
                text = "$percent%",
                color = MirroraPrimaryBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp
            )
        }

        Text(
            text = supportingText,
            style = MaterialTheme.typography.bodySmall,
            color = MirroraTextSecondary,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
