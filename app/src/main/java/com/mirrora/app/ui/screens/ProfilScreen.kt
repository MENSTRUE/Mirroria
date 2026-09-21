package com.mirrora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mirrora.app.BuildConfig
import com.mirrora.app.ui.components.FacePlaceholder
import com.mirrora.app.ui.components.SectionHeader
import com.mirrora.app.ui.components.SettingsRow
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraDanger
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

@Composable
fun ProfilScreen(
    onDeleteHistory: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MirroraBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = "Profil",
            style = MaterialTheme.typography.titleLarge,
            color = MirroraTextPrimary
        )

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 20.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FacePlaceholder(
                shape = CircleShape,
                modifier = Modifier.size(80.dp)
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(
                text = "Pengguna MIRRORA",
                style = MaterialTheme.typography.titleMedium,
                color = MirroraTextPrimary
            )
            Text(
                text = "Kelola preferensi dan data aplikasimu.",
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 28.dp))
        SectionHeader(title = "Preferensi")
        SettingsRow(icon = Icons.Outlined.Language, label = "Bahasa", trailingValue = "Indonesia", onClick = {})
        SettingsRow(icon = Icons.Outlined.DarkMode, label = "Tema aplikasi", trailingValue = "Terang", onClick = {})

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 12.dp))
        SectionHeader(title = "Data")
        SettingsRow(
            icon = Icons.Outlined.Delete,
            label = "Hapus riwayat analisis",
            destructive = true,
            onClick = { showDeleteDialog = true }
        )

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 12.dp))
        SectionHeader(title = "Tentang")
        SettingsRow(icon = Icons.Outlined.Info, label = "Tentang MIRRORA", onClick = {})
        SettingsRow(icon = Icons.Outlined.Lock, label = "Privasi", onClick = {})
        SettingsRow(
            icon = Icons.Outlined.Info,
            label = "Versi aplikasi",
            trailingValue = BuildConfig.VERSION_NAME,
            showChevron = false,
            onClick = null
        )

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(top = 24.dp))
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Hapus riwayat analisis?") },
            text = { Text("Tindakan ini akan menghapus seluruh riwayat analisis secara permanen.") },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteHistory()
                    showDeleteDialog = false
                }) {
                    Text("Hapus", color = MirroraDanger)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }
}
