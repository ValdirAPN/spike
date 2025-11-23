package br.com.spike.presentation.home

import br.com.spike.data.repository.HomeRepositoryImpl
import br.com.spike.domain.repository.HomeRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val homeModule: DI.Module = DI.Module("home-module") {
    bindProvider<HomeRepository> {
        HomeRepositoryImpl(authRepository = instance())
    }

    bindProvider {
        HomeScreenModel(
            homeRepository = instance()
        )
    }
}