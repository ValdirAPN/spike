package br.com.spike.presentation.matchExplorer

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val matchExplorerModule: DI.Module = DI.Module("match-explorer-module") {
    bindProvider {
        MatchExplorerScreenModel(
            matchRepository = instance()
        )
    }
}