package br.com.spike.presentation.splash

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val splashModule: DI.Module = DI.Module("splash-module") {
    bindProvider {
        SplashScreenModel(
            authRepository = instance()
        )
    }
}