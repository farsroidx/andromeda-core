@file:Suppress("unused")

package ir.farsroidx.m31

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.SavedStateHandle
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

// TODO: Andromeda =================================================================== Andromeda ===

internal fun AndromedaKernel.installAndromeda(providers: List<AndromedaProvider>) = startKoin {

    androidLogger(Level.NONE)

    androidContext(this@installAndromeda)

    mutableListOf( provideCommonModule() ).apply {

        this.addAll( providers.map { provider -> provider.getKoinModule() } )

        modules(this)
    }
}

private fun provideCommonModule() = module {

    single<Handler> { Handler( Looper.getMainLooper() ) }

    single<SavedStateHandle> { SavedStateHandle() }

}

// TODO: CommonVariables ======================================================= CommonVariables ===

val Andromeda.uiHandler: Handler by andromedaInjector()

val Andromeda.savedStateHandle: SavedStateHandle by andromedaInjector()