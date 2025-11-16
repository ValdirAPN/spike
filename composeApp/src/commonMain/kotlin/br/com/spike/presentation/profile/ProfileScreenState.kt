package br.com.spike.presentation.profile

import br.com.spike.domain.model.User

data class ProfileScreenState(
    val user: User = User()
)
