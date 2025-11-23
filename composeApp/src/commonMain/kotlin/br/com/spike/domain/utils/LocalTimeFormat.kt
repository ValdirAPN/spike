package br.com.spike.domain.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun LocalDateTime.formatWithDuration(duration: Int): String {
    val startTime = this.time
    val endTime = this.toInstant(TimeZone.currentSystemDefault())
        .plus(value = duration, unit = DateTimeUnit.MINUTE)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .time

    return "$startTime - $endTime"
}