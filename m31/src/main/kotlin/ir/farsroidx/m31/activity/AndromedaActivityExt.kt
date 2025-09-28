@file:Suppress("UNCHECKED_CAST", "DiscouragedApi")

package ir.farsroidx.m31.activity

import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.farsroidx.m31.upperCamelToSnakeCase
import java.lang.reflect.ParameterizedType

@MainThread
internal fun <T: ViewDataBinding> AndromedaActivity<*>.autoInflate(): T {

    val persistentClass : Class<T> = ( javaClass.genericSuperclass as ParameterizedType )
        .actualTypeArguments[0] as Class<T>

    val layoutName = persistentClass.simpleName.upperCamelToSnakeCase().substringBeforeLast("_")

    val layoutResId = resources.getIdentifier(layoutName, "layout", packageName)

    return DataBindingUtil.inflate<T>(layoutInflater, layoutResId, null, false)
        .apply {
            lifecycleOwner = this@autoInflate
            setContentView( root )
        }
}