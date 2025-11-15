package br.com.spike.domain.repository

import br.com.spike.domain.model.User

interface HomeRepository {

    suspend fun loadUserData(): User
}