package br.com.spike.data.mapper

import br.com.spike.data.model.PlayerDto
import br.com.spike.domain.model.Player

fun PlayerDto.toDomain(): Player = Player(
    uid = uid,
    username = username,
    avatarUrl = avatarUrl,
)