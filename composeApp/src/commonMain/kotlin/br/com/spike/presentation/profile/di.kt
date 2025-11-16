package br.com.spike.presentation.profile

import br.com.spike.data.repository.ProfileRepositoryImpl
import br.com.spike.domain.repository.ProfileRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val profileModule: DI.Module = DI.Module("profile-module") {
    bindProvider<ProfileRepository> {
        ProfileRepositoryImpl(
            authRepository = instance()
        )
    }

    bindSingleton {
        ProfileScreenModel(
            profileRepository = instance()
        )
    }
}