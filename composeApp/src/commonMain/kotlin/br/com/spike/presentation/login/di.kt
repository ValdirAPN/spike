package br.com.spike.presentation.login

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val loginModule: DI.Module = DI.Module("login-module") {
    bindProvider {
        LoginScreenModel(
            authRepository = instance()
        )
    }
}