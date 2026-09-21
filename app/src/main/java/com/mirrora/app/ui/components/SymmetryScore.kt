package com.mirrora.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun SymmetryScore(
    percent: Int,
    supportingText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Indeks simetri visual",
                style = MaterialTheme.typography.bodyMedium,
                color = MirroraTextSecondary
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
            fontSize = 44.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = supportingText,
            style = MaterialTheme.typography.bodyMedium,
            color = MirroraTextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
