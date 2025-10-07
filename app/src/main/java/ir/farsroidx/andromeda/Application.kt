package ir.farsroidx.andromeda

import ir.farsroidx.m31.AndromedaExpiration
import ir.farsroidx.m31.AndromedaKernel
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.AndromedaTimeUnit
import ir.farsroidx.m31.andromedaMemoryCacheProvider
import ir.farsroidx.m31.andromedaPreferencesProvider

class Application : AndromedaKernel() {

    /**
     * If the settings for each part are not added,
     * you cannot use that part and you will encounter a crash!
     */
    override fun getAndromedaProviders(): List<AndromedaProvider> {

        return listOf(

            // TODO: MemoryCache: implementation("com.github.farsroidx:andromeda-memory-cache:*.*.*") ==
            andromedaMemoryCacheProvider(
                maxCacheSize   = 10 * 1024 * 1024, // 10MB
                expirationDate = AndromedaExpiration(5, AndromedaTimeUnit.Minutes)
            ),

//            // TODO: FileCache: implementation("com.github.farsroidx:andromeda-file-cache:*.*.*") ======
//            andromedaFileCacheProvider(
//                maxCacheSize   = 10 * 1024 * 1024, // 10MB
//                expirationDate = AndromedaExpiration(5, AndromedaTimeUnit.Seconds)
//            ),
//
            // TODO: Preferences: implementation("com.github.farsroidx:andromeda-preferences:*.*.*") ===
            andromedaPreferencesProvider(
                preferenceName = "farsroidx",
                expirationDate = AndromedaExpiration(1, AndromedaTimeUnit.Day)
            )
        )
    }
}