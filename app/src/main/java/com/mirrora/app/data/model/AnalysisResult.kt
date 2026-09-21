package com.mirrora.app.data.model

import java.util.UUID

data class AnalysisResult(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val time: String,
    val symmetryPercent: Int,
    val facialAreas: List<FacialAreaScore>,
    // Null means: no real photo captured yet -> UI falls back to the neutral silhouette placeholder.
    val imageUri: String? = null
) {
    val supportingText: String
        get() = if (symmetryPercent >= 80) {
            "Kedua sisi terlihat cukup seimbang."
        } else {
            "Terdapat sedikit perbedaan antara kedua sisi."
        }
}
