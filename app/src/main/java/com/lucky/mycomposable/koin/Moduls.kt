package com.lucky.mycomposable.koin

import com.lucky.mycomposable.network.RestApiBuilder
import com.lucky.mycomposable.repository.PhotoRepository
import com.lucky.mycomposable.repository.PhotosRepositoryImpl
import com.lucky.mycomposable.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel{MainViewModel(get())}
}

val networkModule = module {
    single { RestApiBuilder(get()) }
}

val accountModule = module {
    single<PhotoRepository> {PhotosRepositoryImpl(get())}
}