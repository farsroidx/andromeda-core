@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ir.farsroidx.m31.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class AndromedaViewModelFragment <B: ViewDataBinding, V: ViewModel> : AndromedaFragment<B>() {

    /**
     * ViewModel instance associated with this Activity.
     * This property is lazily initialized using the viewModels() delegate,
     * ensuring the ViewModel is scoped to this Activity and lifecycle-aware.
     */
    protected val viewModel: V by viewModels<V>()

}