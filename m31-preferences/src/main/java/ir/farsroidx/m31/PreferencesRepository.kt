@file:Suppress("unused")

package ir.farsroidx.m31

interface PreferencesRepository {

    suspend fun <T : Any> put(key: String, value: T)

    suspend fun <T : Any> get(key: String): T?

    suspend fun <T : Any> get(key: String, alternate: T): T

    suspend fun containsKey(key: String): Boolean

    suspend fun containsKey(vararg keys: String): Boolean

    suspend fun containsKey(keys: List<String>): Boolean

    suspend fun remove(key: String)

    suspend fun remove(vararg keys: String)

    suspend fun remove(keys: List<String>)

    suspend fun clear()

}