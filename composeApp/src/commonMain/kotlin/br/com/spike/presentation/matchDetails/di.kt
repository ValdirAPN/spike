package br.com.spike.presentation.matchDetails

import org.kodein.di.DI
import org.kodein.di.bindProvider

val matchDetailsModule: DI.Module = DI.Module("match-details-module") {
    bindProvider {
        MatchDetailsScreenModel()
    }
}