package com.example.pickmecommentator.presentation.navigation.global

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pickmecommentator.presentation.screens.main.MainScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomNavController: NavHostController,
    startDestination: String = NavRoutes.MainScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.MainScreen.route) {
            MainScreen()
        }
    }
}