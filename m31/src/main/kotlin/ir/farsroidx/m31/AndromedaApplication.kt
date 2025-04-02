package ir.farsroidx.m31

import androidx.multidex.MultiDexApplication
import org.koin.core.KoinApplication

/**
 * The base application class for the Andromeda framework, extending [MultiDexApplication].
 * It initializes Koin dependency injection using Andromeda modules.
 */
abstract class AndromedaApplication : MultiDexApplication() {

    /**
     * Called when the application is created.
     * It initializes Koin dependency injection with the provided Andromeda modules.
     */
    override fun onCreate() {
        super.onCreate()

        // Setup and Start Koin
        onKoinInitialized( installAndromeda( initAndromedaProviders() ) )

    }

    /**
     * Called after Koin has been initialized.
     * Can be overridden by subclasses to customize post-initialization behavior.
     *
     * @param koin The initialized KoinApplication instance.
     */
    protected open fun onKoinInitialized(koin: KoinApplication) {
        // TODO: No changes required by default. Override to customize behavior.
    }

    /**
     * Provides a list of [AndromedaProvider] modules to be installed in Koin.
     * This method must be implemented by subclasses to define the application's dependency graph.
     *
     * @return A list of AndromedaProvider instances.
     */
    open fun initAndromedaProviders(): List<AndromedaProvider> = listOf()

}