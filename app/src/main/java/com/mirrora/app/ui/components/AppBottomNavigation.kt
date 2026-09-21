package com.mirrora.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mirrora.app.navigation.MirroraDestinations
import com.mirrora.app.ui.theme.MirroraPrimaryBlue
import com.mirrora.app.ui.theme.MirroraSurface
import com.mirrora.app.ui.theme.MirroraTextSecondary

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(MirroraDestinations.BERANDA, "Beranda", Icons.Outlined.Home),
    BottomNavItem(MirroraDestinations.SCAN, "Scan", Icons.Outlined.CameraAlt),
    BottomNavItem(MirroraDestinations.RIWAYAT, "Riwayat", Icons.Outlined.History),
    BottomNavItem(MirroraDestinations.PROFIL, "Profil", Icons.Outlined.Person)
)

@Composable
fun AppBottomNavigation(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MirroraSurface,
        contentColor = MirroraTextSecondary,
        tonalElevation = 0.dp
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.label)
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MirroraPrimaryBlue,
                    selectedTextColor = MirroraPrimaryBlue,
                    unselectedIconColor = MirroraTextSecondary,
                    unselectedTextColor = MirroraTextSecondary,
                    indicatorColor = MirroraSurface
                )
            )
        }
    }
}
