package com.karakoca.baseproject.di

import com.karakoca.baseproject.data.local.database.MovieDao
import com.karakoca.baseproject.data.local.database.MovieDatabase
import com.karakoca.baseproject.data.remote.ApiService
import com.karakoca.baseproject.data.remote.RemoteDataSource
import com.karakoca.baseproject.data.repository.MovieRepositoryImpl
import com.karakoca.baseproject.domain.repository.MovieRepository
import com.karakoca.baseproject.domain.usecase.GetMoviesUseCase
import com.karakoca.baseproject.platform.getDatastoreModuleByPlatform
import com.karakoca.baseproject.platform.getRoomDatabase
import com.karakoca.baseproject.platform.provideDispatcher
import com.karakoca.baseproject.presentation.favorite.FavoriteViewModel
import com.karakoca.baseproject.presentation.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModule = module {
    single { RemoteDataSource(get(), get()) }
    single { ApiService() }
}

val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    //useCases
    factory { GetMoviesUseCase() }

    //viewModels
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}

val platformModule = module {
    factory { provideDispatcher() }
    single<MovieDatabase> { getRoomDatabase() }
    single<MovieDao> { get<MovieDatabase>().getDao() }
}

private val sharedModules = listOf(dataModule, domainModule, platformModule)

fun getSharedModules() = sharedModules + getDatastoreModuleByPlatform()