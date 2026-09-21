package com.mirrora.app.data

import com.mirrora.app.data.model.AnalysisResult
import com.mirrora.app.data.model.FacialAreaScore
import com.mirrora.app.data.model.FacialAreaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

/**
 * In-memory data source for analysis results.
 *
 * IMPORTANT: The seeded sample results below are PLACEHOLDER DEMO DATA only,
 * used so the UI has something to render before a real analysis engine exists.
 * When the actual facial-symmetry analysis engine is implemented, replace:
 *   1. the seed data in `sampleResults`
 *   2. the `generatePlaceholderResult()` function
 * with real output from that engine. Nothing else in the UI layer needs to change,
 * since screens only depend on the AnalysisResult / FacialAreaScore models.
 */
object AnalysisRepository {

    private val sampleResults = listOf(
        AnalysisResult(
            date = "12 Sep 2026", time = "14:32", symmetryPercent = 86,
            facialAreas = defaultAreas(88, 82, 85, 80, 83)
        ),
        AnalysisResult(
            date = "10 Sep 2026", time = "09:15", symmetryPercent = 78,
            facialAreas = defaultAreas(80, 75, 79, 76, 77)
        ),
        AnalysisResult(
            date = "8 Sep 2026", time = "21:40", symmetryPercent = 91,
            facialAreas = defaultAreas(93, 90, 92, 89, 90)
        ),
        AnalysisResult(
            date = "5 Sep 2026", time = "16:20", symmetryPercent = 74,
            facialAreas = defaultAreas(76, 71, 75, 73, 72)
        ),
        AnalysisResult(
            date = "2 Sep 2026", time = "10:11", symmetryPercent = 88,
            facialAreas = defaultAreas(90, 85, 88, 86, 87)
        )
    )

    private val _results = MutableStateFlow(sampleResults)
    val results: StateFlow<List<AnalysisResult>> = _results.asStateFlow()

    fun getById(id: String): AnalysisResult? = _results.value.firstOrNull { it.id == id }

    fun deleteAllHistory() {
        _results.value = emptyList()
    }

    /**
     * TODO: Replace this with a call into the real facial symmetry analysis engine.
     * For now it generates a plausible-looking placeholder result so the
     * Scan -> Hasil analisis flow is fully navigable end to end.
     */
    fun generatePlaceholderResult(imageUri: String?): AnalysisResult {
        val score = Random.nextInt(70, 96)
        val jitter = { base: Int -> (base + Random.nextInt(-6, 6)).coerceIn(50, 99) }
        val result = AnalysisResult(
            date = "Hari ini",
            time = "Sekarang",
            symmetryPercent = score,
            facialAreas = defaultAreas(
                jitter(score), jitter(score), jitter(score), jitter(score), jitter(score)
            ),
            imageUri = imageUri
        )
        _results.value = listOf(result) + _results.value
        return result
    }

    private fun defaultAreas(mata: Int, alis: Int, hidung: Int, mulut: Int, rahang: Int) = listOf(
        FacialAreaScore(FacialAreaType.MATA, mata),
        FacialAreaScore(FacialAreaType.ALIS, alis),
        FacialAreaScore(FacialAreaType.HIDUNG, hidung),
        FacialAreaScore(FacialAreaType.MULUT, mulut),
        FacialAreaScore(FacialAreaType.RAHANG, rahang)
    )
}
