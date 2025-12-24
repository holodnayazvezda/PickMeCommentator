package com.example.pickmecommentator.domain.usecase

import com.example.pickmecommentator.data.network.models.TripMessagesListResponse
import com.example.pickmecommentator.domain.repositories.TripsApiRepository

class GetTripMessagesUseCase(private val tripsRepository: TripsApiRepository) {
    suspend operator fun invoke(tripId: String, offset: Int, limit: Int): TripMessagesListResponse {
        return tripsRepository.getTripMessages(tripId.toInt(), offset, limit)
    }
}