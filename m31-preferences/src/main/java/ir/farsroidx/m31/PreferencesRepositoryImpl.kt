@file:Suppress("unused", "UNCHECKED_CAST")

package ir.farsroidx.m31

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

internal class PreferencesRepositoryImpl(
    appContext: Context, preferenceName: String,
    private val gSon: Gson, private val expirationDate: AndromedaExpiration?
) : PreferencesRepository {

    private val Context._dataStore: DataStore<Preferences> by preferencesDataStore(
        name = preferenceName
    )

    private val dataStore: DataStore<Preferences> = appContext._dataStore

    override suspend fun <T: Any> put(key: String, value: T) {

        dataStore.edit { transform ->

            transform[ stringPreferencesKey(key) ] =
                gSon.toJson(
                    PreferencesModel(
                        value, expirationDate.getExpDate()
                    )
                )
        }
    }

    override suspend fun <T : Any> get(key: String, alternate: T): T = get(key) ?: alternate

    override suspend fun <T : Any> get(key: String): T? {

        val dataFlow = dataStore.data
            .catch { throwable ->
                if (throwable is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw throwable
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(key)]
            }

        val value = dataFlow.first() ?: return null

        val deserialize = gSon.fromJson(value, PreferencesModel::class.java)

        return if (deserialize.expireDate.isExpired()) {

            remove(key); null

        } else deserialize.value as T
    }

    override suspend fun containsKey(key: String) =
        dataStore.data
            .map { preference ->
                preference.contains(
                    stringPreferencesKey(key)
                )
            }.first()

    override suspend fun containsKey(vararg keys: String) = containsKey(keys.asList())

    override suspend fun containsKey(keys: List<String>): Boolean {

        var hasKeys = true

        for (key in keys) {

            val hasRecord = dataStore.data
                .map { preference ->
                    preference.contains(
                        stringPreferencesKey(key)
                    )
                }
                .first()

            if (!hasRecord) hasKeys = false
        }

        return hasKeys
    }

    override suspend fun remove(key: String) {

        dataStore.edit { preferences ->

            val dataStoreKey = stringPreferencesKey(key)

            if (preferences.contains(dataStoreKey)) {
                preferences.remove(dataStoreKey)
            }
        }
    }

    override suspend fun remove(vararg keys: String) = remove(keys.asList())

    override suspend fun remove(keys: List<String>) {

        dataStore.edit { preferences ->

            keys.forEach { key ->

                val dataStoreKey = stringPreferencesKey(key)

                if (preferences.contains(dataStoreKey)) {
                    preferences.remove(dataStoreKey)
                }
            }
        }
    }

    override suspend fun clear() {
        dataStore.edit { preferences -> preferences.clear() }
    }
}