package br.com.spike.data.mapper

import br.com.spike.data.model.UserDto
import br.com.spike.domain.model.Player
import br.com.spike.domain.model.User

fun UserDto.toDomain(id: String): User =
    User(
        uid = id,
        name = name,
        username = username,
        avatarUrl = avatarUrl,
    )

fun User.toPlayer(): Player =
    Player(
        uid = uid,
        username = username,
        avatarUrl = avatarUrl
    )