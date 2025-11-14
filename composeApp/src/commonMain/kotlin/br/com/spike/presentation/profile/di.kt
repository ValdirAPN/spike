package br.com.spike.presentation.profile

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val profileModule: DI.Module = DI.Module("profile-module") {
    bindSingleton {
        ProfileScreenModel(
            authService = instance()
        )
    }
}