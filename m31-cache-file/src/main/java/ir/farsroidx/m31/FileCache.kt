@file:Suppress("unused")

package ir.farsroidx.m31

// TODO: Memory ========================================================================= Memory ===

val Andromeda.fileCache: FileCacheRepository by andromedaInjector()

fun andromedaFileCacheProvider(
    maxCacheSize: Int, expiration: AndromedaExpiration? = null
): AndromedaProvider = FileCacheProvider(
    maxCacheSize, expiration
)