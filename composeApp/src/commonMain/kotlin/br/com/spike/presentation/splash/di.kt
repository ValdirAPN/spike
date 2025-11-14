package br.com.spike.presentation.splash

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val splashModule: DI.Module = DI.Module("splash-module") {
    bindSingleton {
        SplashScreenModel(
            authService = instance()
        )
    }
}