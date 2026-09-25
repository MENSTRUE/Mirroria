package com.mirrora.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mirrora.app.navigation.MirroraDestinations
import com.mirrora.app.ui.components.AppBottomNavigation
import com.mirrora.app.ui.screens.BerandaScreen
import com.mirrora.app.ui.screens.DasarAnalisisScreen
import com.mirrora.app.ui.screens.HasilAnalisisScreen
import com.mirrora.app.ui.screens.ProfilScreen
import com.mirrora.app.ui.screens.RiwayatScreen
import com.mirrora.app.ui.screens.ScanScreen

@Composable
fun MirroraApp() {
    val navController = rememberNavController()
    val viewModel: AnalysisViewModel = viewModel()
    val results by viewModel.results.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != null && currentRoute in MirroraDestinations.bottomNavRoutes) {
                AppBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MirroraDestinations.BERANDA,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MirroraDestinations.BERANDA) {
                BerandaScreen(
                    recentResults = results,
                    onStartScan = { navController.navigate(MirroraDestinations.SCAN) },
                    onSeeAllHistory = { navController.navigate(MirroraDestinations.RIWAYAT) },
                    onResultClick = { id -> navController.navigate(MirroraDestinations.hasilRoute(id)) },
                    onSettingsClick = {
                        navController.navigate(MirroraDestinations.PROFIL) { launchSingleTop = true }
                    }
                )
            }

            composable(MirroraDestinations.SCAN) {
                ScanScreen(
                    onClose = { navController.popBackStack() },
                    onPhotoReady = { uri ->
                        val result = viewModel.addPlaceholderResult(uri?.toString())
                        navController.navigate(MirroraDestinations.hasilRoute(result.id)) {
                            popUpTo(MirroraDestinations.BERANDA)
                        }
                    }
                )
            }

            composable(MirroraDestinations.HASIL_ROUTE) { backStackEntry ->
                val resultId = backStackEntry.arguments?.getString("resultId")
                val result = resultId?.let { viewModel.getById(it) }
                if (result != null) {
                    HasilAnalisisScreen(
                        result = result,
                        onBack = { navController.popBackStack() },
                        onExplainAnalysis = { navController.navigate(MirroraDestinations.DASAR_ANALISIS) }
                    )
                }
            }

            composable(MirroraDestinations.RIWAYAT) {
                RiwayatScreen(
                    results = results,
                    onResultClick = { id -> navController.navigate(MirroraDestinations.hasilRoute(id)) }
                )
            }

            composable(MirroraDestinations.PROFIL) {
                ProfilScreen(
                    onDeleteHistory = { viewModel.deleteAllHistory() },
                    onAnalysisBasisClick = { navController.navigate(MirroraDestinations.DASAR_ANALISIS) }
                )
            }

            composable(MirroraDestinations.DASAR_ANALISIS) {
                DasarAnalisisScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
