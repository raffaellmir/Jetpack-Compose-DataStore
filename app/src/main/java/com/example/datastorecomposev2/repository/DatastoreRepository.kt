package com.example.datastorecomposev2.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    companion object Keys {
        val username = stringPreferencesKey("user_name")
    }

    suspend fun setUserName(userName: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.username] = userName
        }
    }

    val getUserName: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[Keys.username] ?: "nothing"
        }
}