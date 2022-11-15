package com.skillbox.aslanbolurov.rickymortycompose.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AllScreen(
    val route: String,
    val tittle: String,
    val icon: ImageVector
) {
    object MainAllScreen: AllScreen(
        route = "main_screen",
        tittle = "Characters",
        icon = Icons.Default.Face
    )

    object DetailAllScreen: AllScreen(
        route = "detail_screen",
        tittle = "Characters",
        icon = Icons.Default.List
    )

    object LocationScreen: AllScreen(
        route = "location_screen",
        tittle = "Locations",
        icon = Icons.Default.LocationOn
    )
}
