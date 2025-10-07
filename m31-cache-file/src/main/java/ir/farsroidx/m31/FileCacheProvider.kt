@file:Suppress("unused")

package ir.farsroidx.m31

import org.koin.core.module.Module
import org.koin.dsl.module

internal class FileCacheProvider(
    private val maxCacheSize: Int,
    private val expiration: AndromedaExpiration?
) : AndromedaProvider {

    override fun getKoinModule(): Module = module {

        single<FileCacheRepository> {
            FileCacheRepositoryImpl(maxCacheSize, expiration)
        }
    }
}