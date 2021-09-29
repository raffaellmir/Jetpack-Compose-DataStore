package com.example.datastorecomposev2.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object Keys {
        val username = stringPreferencesKey("user_name")
        val counter = intPreferencesKey("counter")
    }

    suspend fun setUserName(userName: String) {
        context.dataStore.edit { settings ->
            settings[username] = userName
        }
    }

    val getUserName: Flow<String> = context.dataStore.data
        .map { settings ->
            settings[username] ?: "nothing"
        }

    ////////

    val counterFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[counter] ?: 0
        }

    suspend fun incrementCounter() {
        context.dataStore.edit { settings ->
            val currentCounterValue = settings[counter] ?: 0
            settings[counter] = currentCounterValue + 1
        }
    }
}