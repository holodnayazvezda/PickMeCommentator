package com.example.pickmecommentator.presentation.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentTripMessagesScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions.ListOfSessionsScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.trip_messages.TripMessagesScreen

@Composable
fun BottomNavigationHost(
    modifier: Modifier,
    bottomNavController: NavHostController,
    startDestination: String = BottomNavRoutes.CurrentSessionScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = bottomNavController,
        startDestination = startDestination
    ) {
        composable(BottomNavRoutes.CurrentSessionScreen.route) {
            CurrentTripMessagesScreen()
        }

        composable(BottomNavRoutes.ListOfSessionsScreen.route) {
            ListOfSessionsScreen()
        }
        composable(BottomNavRoutes.TripMessagesScreen.route) {
            TripMessagesScreen()
        }
    }
}