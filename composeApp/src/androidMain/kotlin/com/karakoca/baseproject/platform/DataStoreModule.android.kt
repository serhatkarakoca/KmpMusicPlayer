package com.karakoca.baseproject.platform

import com.karakoca.baseproject.data.local.AppPreferencesRepository
import com.karakoca.baseproject.data.local.createDataStore
import com.karakoca.baseproject.data.local.dataStoreFileName
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun getDatastoreModuleByPlatform(): Module = module {

    single {
        createDataStore(
            producePath = {
                applicationContext.filesDir?.resolve(dataStoreFileName)?.absolutePath
                    ?: throw Exception("Couldn't get Android Datastore context.")
            }
        )
    }

    single { AppPreferencesRepository(get()) }

}