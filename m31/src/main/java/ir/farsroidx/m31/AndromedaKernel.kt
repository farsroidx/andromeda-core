package ir.farsroidx.m31

import androidx.multidex.MultiDexApplication
import org.koin.core.KoinApplication

abstract class AndromedaKernel : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        onKoinApplication( installAndromeda( getAndromedaProviders() ) )

    }

    protected open fun onKoinApplication(koin: KoinApplication) {
        // TODO: Nothing to Change...
    }

    abstract fun getAndromedaProviders(): List<AndromedaProvider>

}