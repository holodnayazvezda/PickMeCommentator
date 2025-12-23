package com.example.pickmecommentator.domain.usecase

import com.example.pickmecommentator.data.network.models.TripListResponse
import com.example.pickmecommentator.domain.repositories.TripsApiRepository

class GetTripsUseCase(
    private val tripsRepository: TripsApiRepository
) {
    suspend operator fun invoke(offset: Int, limit: Int): TripListResponse {
        return tripsRepository.getTrips(offset, limit)
    }
}
