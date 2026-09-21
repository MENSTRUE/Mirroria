package com.mirrora.app

import androidx.lifecycle.ViewModel
import com.mirrora.app.data.AnalysisRepository
import com.mirrora.app.data.model.AnalysisResult
import kotlinx.coroutines.flow.StateFlow

class AnalysisViewModel : ViewModel() {

    val results: StateFlow<List<AnalysisResult>> = AnalysisRepository.results

    fun getById(id: String): AnalysisResult? = AnalysisRepository.getById(id)

    fun addPlaceholderResult(imageUri: String?): AnalysisResult =
        AnalysisRepository.generatePlaceholderResult(imageUri)

    fun deleteAllHistory() = AnalysisRepository.deleteAllHistory()
}
