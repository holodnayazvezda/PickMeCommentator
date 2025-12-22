package com.example.pickmecommentator.common.di

import com.example.pickmecommentator.data.network.repository.SocketRepositoryImpl
import com.example.pickmecommentator.domain.repositories.SocketRepository
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentSessionScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SocketRepository> { SocketRepositoryImpl() }

    viewModel { CurrentSessionScreenViewModel(socketRepository = get()) }
}