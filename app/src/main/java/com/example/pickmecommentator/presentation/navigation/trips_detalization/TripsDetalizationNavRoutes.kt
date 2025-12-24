package com.example.pickmecommentator.presentation.navigation.trips_detalization

sealed class TripsDetalizationNavRoutes(val route: String) {
    object ListOfTripsScreen : TripsDetalizationNavRoutes("list_of_trips")
    object TripMessagesScreen : TripsDetalizationNavRoutes("trip_messages/{tripId}") {
        fun withArgs(tripId: Int): String {
            return "trip_messages/$tripId"
        }
    }
}