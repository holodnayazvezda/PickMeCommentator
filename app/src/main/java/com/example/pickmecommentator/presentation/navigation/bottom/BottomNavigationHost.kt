package com.example.pickmecommentator.presentation.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentSessionScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions.ListOfSessionsScreen

@Composable
fun BottomNavigationHost(
    modifier: Modifier,
    bottomNavController: NavHostController,
    startDestination: String = BottomNavRoutes.CurrentSessionScreen.route
) {
    NavHost(
        navController = bottomNavController,
        startDestination = startDestination
    ) {
        composable(BottomNavRoutes.CurrentSessionScreen.route) {
            CurrentSessionScreen()
        }

        composable(BottomNavRoutes.ListOfSessionsScreen.route) {
            ListOfSessionsScreen()
        }
    }
}