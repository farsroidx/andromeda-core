@file:Suppress("UNCHECKED_CAST", "DiscouragedApi")

package ir.farsroidx.m31.activity

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import java.lang.reflect.ParameterizedType
import kotlin.jvm.kotlin

@MainThread
internal fun <T : ViewModel> AndromedaViewModelActivity<*, *>.viewModels(): Lazy<T> {

    val type = (this.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[1]

    val clazz = ( type as Class<T> ).kotlin

    return ViewModelLazy( clazz ,
        { viewModelStore                  },
        { defaultViewModelProviderFactory },
        { defaultViewModelCreationExtras  }
    )
}