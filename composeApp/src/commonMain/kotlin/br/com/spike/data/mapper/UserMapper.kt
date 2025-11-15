package br.com.spike.data.mapper

import br.com.spike.data.model.UserDto
import br.com.spike.domain.model.User

fun UserDto.toDomain(id: String): User =
    User(
        id = id,
        name = name,
        username = username,
        avatarUrl = avatarUrl,
    )