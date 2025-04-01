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

/**
 * Initializes and configures Koin for dependency injection.
 * It installs all required Andromeda modules provided by the application.
 *
 * @param providers List of AndromedaProvider modules to be included in the Koin setup.
 */
internal fun AndromedaApplication.installAndromeda(providers: List<AndromedaProvider>) = startKoin {

    // Enable Koin logging only in DEBUG mode
    if (BuildConfig.DEBUG) androidLogger(Level.DEBUG) else androidLogger(Level.NONE)

    // Set Android context for dependency injection
    androidContext(this@installAndromeda)

    // Register provided modules along with the common module
    modules( listOf( provideCommonModule() ) + providers.map { it.initKoinModule() } )

}

/**
 * Provides common dependencies that are used across the application.
 */
private fun provideCommonModule() = module {

    // Provides a main-thread handler for UI operations
    single<Handler> { Handler( Looper.getMainLooper() ) }

    // Provides a main-thread handler for UI operations
    single<SavedStateHandle> { SavedStateHandle() }

}

// TODO: CommonVariables ======================================================= CommonVariables ===

/**
 * Global UI Handler to execute tasks on the main thread.
 */
val Andromeda.uiHandler: Handler by provide()

/**
 * Global SavedStateHandle to execute tasks on the main thread.
 */
val Andromeda.savedStateHandle: SavedStateHandle by provide()