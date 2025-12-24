package com.example.pickmecommentator.presentation.navigation.trips_detalization

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.list_of_trips.ListOfTripsScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages.TripMessagesScreen

@Composable
fun TripsDetalizationNavigationHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = TripsDetalizationNavRoutes.ListOfTripsScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(TripsDetalizationNavRoutes.ListOfTripsScreen.route) {
            ListOfTripsScreen(navController)
        }
        composable(TripsDetalizationNavRoutes.TripMessagesScreen.route) {
            TripMessagesScreen()
        }
    }
}