package br.com.spike.presentation.matchForm

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val matchFormModule: DI.Module = DI.Module("match-form-module") {
    bindProvider {
        MatchFormScreenModel(
            matchRepository = instance()
        )
    }
}