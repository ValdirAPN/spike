package br.com.spike.domain.repository

import br.com.spike.data.model.MatchDto
import br.com.spike.domain.model.Match

interface MatchRepository {

    suspend fun create(match: MatchDto)
    suspend fun update(id: String, match: MatchDto): Match?
    suspend fun getAll(): List<Match>
    suspend fun get(id: String): Match?
    suspend fun getUpcomingMatches(): List<Match>
}