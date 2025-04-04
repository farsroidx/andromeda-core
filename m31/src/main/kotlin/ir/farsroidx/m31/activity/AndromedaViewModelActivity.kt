@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER", "DEPRECATION", "unused")

package ir.farsroidx.m31.activity

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class AndromedaViewModelActivity <B: ViewDataBinding, V: ViewModel> : AndromedaActivity<B>() {

    /**
     * ViewModel instance associated with this Activity.
     * This property is lazily initialized using the viewModels() delegate,
     * ensuring the ViewModel is scoped to this Activity and lifecycle-aware.
     */
    protected val viewModel: V by viewModels<V>()

}