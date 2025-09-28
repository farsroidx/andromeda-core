@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ir.farsroidx.m31.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.apply
import kotlin.isInitialized

abstract class AndromedaFragment <B: ViewDataBinding> : Fragment() {

    private lateinit var _binding : B

    protected val binding : B by lazy { _binding }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (!this::_binding.isInitialized) {
            _binding = autoInflate()
            binding.onInitialized( savedInstanceState )
        } else {
            binding.onReInitializing( savedInstanceState )
        }
        return binding.root
    }

    /** After onCreate called */
    protected abstract fun B.onInitialized(savedInstanceState: Bundle?)

    /** It is called every time the fragment is created */
    protected open fun B.onReInitializing(savedInstanceState: Bundle?) {

    }

    protected fun binding(block: B.() -> Unit) = binding.apply(block)
}