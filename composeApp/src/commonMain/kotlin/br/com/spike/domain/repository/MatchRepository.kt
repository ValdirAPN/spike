package br.com.spike.domain.repository

import br.com.spike.data.model.MatchDto

interface MatchRepository {

    suspend fun create(match: MatchDto)
}