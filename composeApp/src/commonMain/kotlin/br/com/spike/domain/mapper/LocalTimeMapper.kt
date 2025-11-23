package br.com.spike.domain.mapper

import kotlinx.datetime.LocalTime

private const val MINUTES_IN_AN_HOUR = 60

fun LocalTime.toMinutes(): Int = this.hour * MINUTES_IN_AN_HOUR + this.minute