package br.com.spike.presentation.signUp

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val signUpModule: DI.Module = DI.Module("sign-up-module") {
    bindProvider {
        SignUpScreenModel(
            authRepository = instance()
        )
    }
}