@file:Suppress("unused")

package ir.farsroidx.m31

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

internal class PreferencesProvider(
    private val preferenceName: String,
    private val expirationDate: AndromedaExpiration?
) : AndromedaProvider {

    override fun getKoinModule(): Module = module {

        single<Gson> {
            GsonBuilder().setLenient().create()
        }

        single<PreferencesRepository> {
            PreferencesRepositoryImpl(
                get(), preferenceName, get(), expirationDate
            )
        }
    }
}