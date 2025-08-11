package com.karakoca.baseproject.di

import com.karakoca.baseproject.data.local.database.MusicDao
import com.karakoca.baseproject.data.local.database.MusicDatabase
import com.karakoca.baseproject.data.remote.ApiService
import com.karakoca.baseproject.data.remote.RemoteDataSource
import com.karakoca.baseproject.data.repository.MusicRepositoryImpl
import com.karakoca.baseproject.domain.repository.MusicRepository
import com.karakoca.baseproject.domain.usecase.SearchUseCase
import com.karakoca.baseproject.platform.getDatastoreModuleByPlatform
import com.karakoca.baseproject.platform.getRoomDatabase
import com.karakoca.baseproject.platform.provideDispatcher
import com.karakoca.baseproject.presentation.favorite.FavoriteViewModel
import com.karakoca.baseproject.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val dataModule = module {
    single { RemoteDataSource(get(), get()) }
    single { ApiService() }
}

val domainModule = module {
    single<MusicRepository> { MusicRepositoryImpl(get()) }

    //useCases
    factory { SearchUseCase() }

    //viewModels
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoriteViewModel)
}

val platformModule = module {
    factory { provideDispatcher() }
    single<MusicDatabase> { getRoomDatabase() }
    single<MusicDao> { get<MusicDatabase>().getDao() }
}

private val sharedModules = listOf(dataModule, domainModule, platformModule)

fun getSharedModules() = sharedModules + getDatastoreModuleByPlatform()