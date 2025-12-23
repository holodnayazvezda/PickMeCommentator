package com.example.pickmecommentator.data.network.repository

import com.example.pickmecommentator.data.network.api.TripsApiService
import com.example.pickmecommentator.data.network.models.Trip
import com.example.pickmecommentator.data.network.models.TripListResponse
import com.example.pickmecommentator.data.network.models.TripMessagesListResponse
import com.example.pickmecommentator.domain.repositories.TripsApiRepository

class TripsApiRepositoryImpl(
    private val apiService: TripsApiService
) : TripsApiRepository {

    override suspend fun getTrips(offset: Int, limit: Int): TripListResponse {
        return apiService.getTrips(offset = offset, limit = limit)
    }

    override suspend fun getTrip(tripId: Int): Trip {
        return apiService.geTrip(tripId = tripId)
    }

    override suspend fun getTripMessages(tripId: Int, offset: Int, limit: Int): TripMessagesListResponse {
        return apiService.getTripMessages(tripId = tripId, offset = offset, limit = limit)
    }
}
