@file:Suppress("unused")

package ir.farsroidx.m31

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.gson.reflect.TypeToken
import kotlin.properties.ReadWriteProperty

// TODO: Preferences =============================================================== Preferences ===

val Andromeda.preferences: PreferencesRepository by andromedaInjector()

fun andromedaPreferencesProvider(
    preferenceName: String,
    expirationDate: AndromedaExpiration? = null
): AndromedaProvider {

    if (expirationDate != null && expirationDate.value <= 0) {
        throw IllegalArgumentException("The value of 'AndromedaExpiration.value' cannot be zero or less.")
    }

    return PreferencesProvider(preferenceName, expirationDate)
}

// TODO: Additives =================================================================== Additives ===

inline fun <reified T> LifecycleOwner.savable(
    name: String? = null, defaultValue: T? = null
): ReadWriteProperty<Any, T> {

    val defValue: T? = when(T::class) {
        String::class  -> ""    as T
        Boolean::class -> false as T
        Int::class     -> 0     as T
        Float::class   -> 0F    as T
        Double::class  -> 0.0   as T
        Long::class    -> 0L    as T
        else           -> defaultValue
    }

    if (defValue == null)
        throw IllegalArgumentException(
            "To store this type of data, you must set 'defaultValue'."
        )

    val gSonType = object : TypeToken< AndromedaSerialized<T> >() {}.type

    return PreferencesProperty(
        this.lifecycleScope, key = name, defaultValue = defaultValue ?: defValue, gSonType
    )
}