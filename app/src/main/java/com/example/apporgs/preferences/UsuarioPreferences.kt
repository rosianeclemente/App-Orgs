package com.example.apporgs.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import java.util.prefs.Preferences
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sessao_usuario")

val usuarioLogadoPreferences = stringPreferencesKey("usuarioLogado")