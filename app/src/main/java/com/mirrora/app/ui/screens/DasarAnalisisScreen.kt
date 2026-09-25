package com.mirrora.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mirrora.app.ui.theme.MirroraBackground
import com.mirrora.app.ui.theme.MirroraBlueLight
import com.mirrora.app.ui.theme.MirroraBorder
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraSurface
import com.mirrora.app.ui.theme.MirroraTextPrimary
import com.mirrora.app.ui.theme.MirroraTextSecondary

private data class AnalysisBasisStep(
    val title: String,
    val description: String
)

private val analysisBasisSteps = listOf(
    AnalysisBasisStep(
        title = "Foto wajah",
        description = "Analisis dimulai dari satu foto wajah yang menghadap kamera dengan posisi sejelas dan senetral mungkin."
    ),
    AnalysisBasisStep(
        title = "Penyelarasan wajah",
        description = "Posisi, kemiringan, dan skala wajah perlu dinormalisasi agar sisi kiri dan kanan dapat dibandingkan pada acuan yang sama."
    ),
    AnalysisBasisStep(
        title = "Landmark wajah",
        description = "Titik-titik acuan wajah digunakan untuk merepresentasikan posisi bagian penting seperti mata, alis, hidung, mulut, dan rahang."
    ),
    AnalysisBasisStep(
        title = "Perbandingan kiri dan kanan",
        description = "Pasangan titik atau area yang bersesuaian pada sisi kiri dan kanan dibandingkan untuk melihat perbedaan relatifnya."
    ),
    AnalysisBasisStep(
        title = "Skor per area",
        description = "Perbandingan kemudian diringkas menjadi informasi per area wajah: mata, alis, hidung, mulut, dan rahang."
    ),
    AnalysisBasisStep(
        title = "Indeks simetri visual",
        description = "Nilai per area selanjutnya dapat digabungkan menjadi satu indeks visual untuk memudahkan pembacaan hasil."
    )
)

@Composable
fun DasarAnalisisScreen(
    onBack: () -> Unit
) {
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
                text = "Dasar analisis",
                style = MaterialTheme.typography.titleMedium,
                color = MirroraTextPrimary,
                modifier = Modifier.weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "MIRRORA menganalisis apa?",
                style = MaterialTheme.typography.titleLarge,
                color = MirroraTextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "MIRRORA bukan sistem pengenalan identitas. Fokusnya adalah visualisasi keseimbangan struktur wajah dengan membandingkan sisi kiri dan kanan.",
                style = MaterialTheme.typography.bodyMedium,
                color = MirroraTextSecondary
            )
            Spacer(modifier = Modifier.height(20.dp))
            InfoCard(
                title = "Yang menjadi dasar",
                text = "Posisi landmark wajah, hubungan antarbagian wajah, serta perbedaan relatif antara area kiri dan kanan setelah wajah diselaraskan."
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Alur analisis",
                style = MaterialTheme.typography.titleMedium,
                color = MirroraTextPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            analysisBasisSteps.forEachIndexed { index, step ->
                AnalysisStepRow(index + 1, step.title, step.description)
            }
            Spacer(modifier = Modifier.height(20.dp))
            PrototypeNotice()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Catatan",
                style = MaterialTheme.typography.titleMedium,
                color = MirroraTextPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Indeks simetri visual tidak dimaksudkan sebagai ukuran kecantikan, kesehatan, kelayakan, atau kualitas seseorang. Ekspresi, sudut kamera, pencahayaan, rambut, aksesori, dan pose dapat memengaruhi hasil visual.",
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary
            )
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun InfoCard(title: String, text: String) {
    val shape = RoundedCornerShape(18.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MirroraBlueLight, shape)
            .border(1.dp, MirroraBorder, shape)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = null,
            tint = MirroraPrimaryBlue,
            modifier = Modifier.size(20.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MirroraTextPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun AnalysisStepRow(number: Int, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(MirroraBlueLight, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MirroraPrimaryBlue,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MirroraTextPrimary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MirroraTextSecondary,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}

@Composable
private fun PrototypeNotice() {
    val shape = RoundedCornerShape(18.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MirroraSurface, shape)
            .border(1.dp, MirroraBorder, shape)
            .padding(16.dp)
    ) {
        Text(
            text = "Status versi saat ini",
            style = MaterialTheme.typography.bodyLarge,
            color = MirroraTextPrimary,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "MIRRORA saat ini masih berupa prototipe UI. Skor analisis dan detail area yang tampil masih berasal dari data simulasi/dummy. Engine pengukuran landmark dan perhitungan simetri otomatis belum diimplementasikan.",
            style = MaterialTheme.typography.bodySmall,
            color = MirroraTextSecondary,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
