@file:Suppress("unused")

package ir.farsroidx.m31

interface FileCacheRepository {

    fun <T : Any> store(key: String, value: T)

    fun <T : Any> get(key: String, alternate: T): T

    fun containsKey(keys: List<String>): Boolean

    fun containsKey(vararg keys: String): Boolean

    fun remove(keys: List<String>)

    fun remove(vararg keys: String)

    fun clear()

    fun createCount(): Int

    fun putCount(): Int

    fun hitCount(): Int

    fun missCount(): Int

    fun evictionCount(): Int

    fun resize(newCacheSize: Int)

    fun trimToSize(newCacheSize: Int)

    fun getRecords(): Map<String, Any>
}