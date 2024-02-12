package dev.silori.selfaudit.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.silori.selfaudit.navigation.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

val Context.dataStore1: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreManager(context: Context) {

    private val dataStore = context.dataStore1

    private object PreferenceKey {
        val startDestinationKey = stringPreferencesKey(name = "start_destination")
    }

    suspend fun saveStartDestination(destination: Destination) {
        withContext(Dispatchers.IO) {
            dataStore.edit { pref ->
                pref[PreferenceKey.startDestinationKey] = destination.name
            }
        }
    }

    fun readStartDestination(): Flow<Destination> {
        return dataStore.data.catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { pref ->
                val destination = Destination.valueOf(
                    pref[PreferenceKey.startDestinationKey] ?: Destination.OnBoardingScreen.name
                )
                destination
            }
    }


}