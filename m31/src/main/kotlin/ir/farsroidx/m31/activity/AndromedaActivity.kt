@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER", "DEPRECATION", "unused")

package ir.farsroidx.m31.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import kotlin.apply

abstract class AndromedaActivity <B: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding : B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {

        onBeforeInitializing(savedInstanceState)

        super.onCreate(savedInstanceState)

        binding = autoInflate()

        binding.onInitialized()
    }

    /** Before onCreate called */
    protected open fun onBeforeInitializing(savedInstanceState: Bundle?) {

    }

    /** After onCreate called */
    protected abstract fun B.onInitialized()

    protected fun binding(block: B.() -> Unit) = binding.apply(block)
}