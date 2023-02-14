package com.example.dd2composetest.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

const val PDS = "pds"
val KEY_USER_NAME = stringPreferencesKey("userName")

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PDS)
