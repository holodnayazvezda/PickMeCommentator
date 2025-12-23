package com.example.pickmecommentator.domain.repositories

import com.example.pickmecommentator.data.network.models.Trip
import com.example.pickmecommentator.data.network.models.TripListResponse
import com.example.pickmecommentator.data.network.models.TripMessagesListResponse

interface TripsApiRepository {
    suspend fun getTrips(offset: Int, limit: Int): TripListResponse

    suspend fun getTrip(tripId: Int): Trip

    suspend fun getTripMessages(tripId: Int, offset: Int, limit: Int): TripMessagesListResponse
}