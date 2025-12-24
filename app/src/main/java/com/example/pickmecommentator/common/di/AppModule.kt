package com.example.pickmecommentator.common.di

import com.example.pickmecommentator.common.Constants.TRIPS_API_SERVER_URL
import com.example.pickmecommentator.data.network.api.TripsApiService
import com.example.pickmecommentator.data.network.repository.SocketRepositoryImpl
import com.example.pickmecommentator.data.network.repository.TripsApiRepositoryImpl
import com.example.pickmecommentator.domain.repositories.SocketRepository
import com.example.pickmecommentator.domain.repositories.TripsApiRepository
import com.example.pickmecommentator.domain.usecase.FormatDateUseCase
import com.example.pickmecommentator.domain.usecase.FormatTimestampUseCase
import com.example.pickmecommentator.domain.usecase.GetTripMessagesUseCase
import com.example.pickmecommentator.domain.usecase.GetTripsUseCase
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_trip_messages.CurrentTripMessagesScreenViewModel
import com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.list_of_trips.ListOfTripsScreenViewModel
import com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages.TripMessagesScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Sockets
    single<SocketRepository> { SocketRepositoryImpl() }

    // Network
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(TRIPS_API_SERVER_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<TripsApiService> {
        get<Retrofit>().create(TripsApiService::class.java)
    }

    single<TripsApiRepository> { TripsApiRepositoryImpl(apiService = get()) }

    // UseCases
    factory { FormatTimestampUseCase() }
    factory { FormatDateUseCase() }
    factory { GetTripsUseCase(tripsRepository = get()) }
    factory { GetTripMessagesUseCase(tripsRepository = get()) }

    // ViewModels
    viewModel { CurrentTripMessagesScreenViewModel(socketRepository = get()) }
    viewModel { ListOfTripsScreenViewModel(getTripsUseCase = get()) }
    viewModel { TripMessagesScreenViewModel(savedStateHandle = get(), getTripMessagesUseCase = get()) }
}
