package com.example.pickmecommentator.data.network.api

import com.example.pickmecommentator.data.network.models.TripMessagesListResponse
import com.example.pickmecommentator.data.network.models.Trip
import com.example.pickmecommentator.data.network.models.TripListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TripsApiService {
    @GET("trips")
    suspend fun getTrips(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): TripListResponse

    @GET("/trips/{trip_id}")
    suspend fun geTrip(
        @Path("trip_id") tripId: Int
    ): Trip

    @GET("/trips/{trip_id}/messages")
    suspend fun getTripMessages(
        @Path("trip_id") tripId: Int,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): TripMessagesListResponse
}
