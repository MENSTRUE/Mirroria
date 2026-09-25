package com.mirrora.app.navigation

object MirroraDestinations {
    const val BERANDA = "beranda"
    const val SCAN = "scan"
    const val RIWAYAT = "riwayat"
    const val PROFIL = "profil"
    const val DASAR_ANALISIS = "dasar_analisis"
    const val HASIL_ROUTE = "hasil/{resultId}"

    fun hasilRoute(resultId: String) = "hasil/$resultId"

    val bottomNavRoutes = setOf(BERANDA, RIWAYAT, PROFIL)
}
