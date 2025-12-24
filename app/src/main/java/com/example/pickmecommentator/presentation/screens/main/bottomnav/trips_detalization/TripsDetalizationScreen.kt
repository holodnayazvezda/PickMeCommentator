package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pickmecommentator.presentation.navigation.trips_detalization.TripsDetalizationNavigationHost

@Composable
fun TripsDetalizationScreen(
    navController: NavHostController = rememberNavController()
) {
    TripsDetalizationNavigationHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController
    )
}