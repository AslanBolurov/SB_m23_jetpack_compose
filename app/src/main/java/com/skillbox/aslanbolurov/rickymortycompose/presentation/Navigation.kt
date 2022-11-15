package com.skillbox.aslanbolurov.rickymortycompose.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skillbox.aslanbolurov.rickymortycompose.presentation.screens.AllScreen
import com.skillbox.aslanbolurov.rickymortycompose.presentation.screens.CharacterListScreen
import com.skillbox.aslanbolurov.rickymortycompose.presentation.screens.DetailScreen
import com.skillbox.aslanbolurov.rickymortycompose.presentation.screens.LocationScreen


@Composable
fun Navigation (viewModel: MainViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AllScreen.MainAllScreen.route
    ) {
        composable(route = AllScreen.MainAllScreen.route) {
            CharacterListScreen(navController, viewModel)
        }
        composable(route = AllScreen.DetailAllScreen.route) {
            DetailScreen(viewModel)
        }
        composable(route = AllScreen.LocationScreen.route) {
            LocationScreen(viewModel)
        }
    }
}
