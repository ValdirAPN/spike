package br.com.spike.presentation.login

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val loginModule: DI.Module = DI.Module("login-module") {
    bindSingleton {
        LoginScreenModel(
            authService = instance()
        )
    }
}