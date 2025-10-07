@file:Suppress("unused", "UNCHECKED_CAST")

package ir.farsroidx.m31

import androidx.collection.LruCache

internal class FileCacheRepositoryImpl(
    maxCacheSize: Int, private val expiration: AndromedaExpiration?,
) : FileCacheRepository {

    private val memoryCache = LruCache<String, FileCacheModel<*>>(maxCacheSize)

    override fun <T : Any> store(key: String, value: T) {
        memoryCache.put(
            key, FileCacheModel(
                value, expiration.getExpDate()
            )
        )
    }

    override fun <T : Any> get(key: String, alternate: T): T {

        return try {

            if (memoryCache[key] != null) {

                val data = memoryCache[key] as FileCacheModel<T>

                if (data.expireDate.isExpired()) {

                    this.remove(key)

                    alternate

                } else data.value

            } else alternate

        } catch (e: Exception) { e.printStackTrace(); alternate }
    }

    override fun containsKey(vararg keys: String) = containsKey(keys.asList())

    override fun containsKey(keys: List<String>): Boolean {

        var hasKeys = true

        keys.forEach { key ->

            if (memoryCache[key] == null) {

                hasKeys = false
            }
        }

        return hasKeys
    }

    override fun remove(vararg keys: String) = remove(keys.toList())

    override fun remove(keys: List<String>) {
        keys.forEach {
            memoryCache.remove(it)
        }
    }

    override fun clear() = memoryCache.evictAll()

    override fun createCount(): Int = memoryCache.createCount()

    override fun putCount(): Int = memoryCache.putCount()

    override fun hitCount(): Int = memoryCache.hitCount()

    override fun missCount(): Int = memoryCache.missCount()

    override fun evictionCount(): Int = memoryCache.evictionCount()

    override fun resize(newCacheSize: Int) = memoryCache.resize(newCacheSize)

    override fun trimToSize(newCacheSize: Int) = memoryCache.trimToSize(newCacheSize)

    override fun getRecords(): Map<String, Any> {
        return memoryCache.snapshot().mapValues {
            it.value.value
        }
    }
}