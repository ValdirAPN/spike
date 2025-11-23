package br.com.spike.domain.repository

import br.com.spike.data.model.MatchDto
import br.com.spike.domain.model.Match

interface MatchRepository {

    suspend fun create(match: MatchDto)
    suspend fun getAll(): List<Match>
}