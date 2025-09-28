@file:Suppress("UNUSED_PARAMETER")

package ir.farsroidx.m31

import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Type
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@RestrictTo(RestrictTo.Scope.LIBRARY)
class PreferencesProperty <T> (
    private val lifecycleScope: LifecycleCoroutineScope,
    private val key: String?,
    private val defaultValue: T,
    private val gSonType: Type,
) : ReadWriteProperty<Any, T> {

    private val gSon: Gson by andromedaInjector()

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        runBlocking(Dispatchers.Default) { getSuspendValue(thisRef, property) }

    private suspend fun getSuspendValue(thisRef: Any, property: KProperty<*>): T {

        val jsonString = Andromeda.preferences.get( getPropertyKey(thisRef, property), "" )

        return if (jsonString != "") {

            gSon.fromJson < AndromedaSerialized<T> > ( jsonString, gSonType ).data

        } else defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {

        lifecycleScope.launch(Dispatchers.Default) {

            Andromeda.preferences.put(
                getPropertyKey(thisRef, property),
                gSon.toJson( AndromedaSerialized( value ), gSonType )
            )
        }
    }

    private fun getPropertyKey(thisRef: Any, property: KProperty<*>) =
        "property-${key ?: property.name}"
}