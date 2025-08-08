package com.karakoca.baseproject.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

interface AppPreferences {
    suspend fun setUserId(id: String)
    suspend fun getUserId(): String
}

data class AppPreferencesData(
    val userId: String = "",
)

class AppPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : KoinComponent, AppPreferences {


    private companion object PreferencesKeys {
        val USER_ID = stringPreferencesKey("userId")
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }

    /**
     * Use this if you don't want to observe a flow.
     */
    private suspend fun fetchInitialPreferences() =
        mapAppPreferences(dataStore.data.first().toPreferences())

    /**
     * Get the user preferences flow. When it's collected, keys are mapped to the
     * [UserPreferences] data class.
     */
    val userPreferencesFlow: Flow<AppPreferencesData> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapAppPreferences(preferences)
        }


    override suspend fun setUserId(id: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }

    override suspend fun getUserId(): String {
        return fetchInitialPreferences().userId
    }


    /**
     * Get the preferences key, then map it to the data class.
     */
    private fun mapAppPreferences(preferences: Preferences): AppPreferencesData {
        val userId = preferences[PreferencesKeys.USER_ID] ?: ""

        return AppPreferencesData(
            userId
        )
    }
}