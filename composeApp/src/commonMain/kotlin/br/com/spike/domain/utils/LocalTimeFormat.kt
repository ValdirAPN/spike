package br.com.spike.domain.utils

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun LocalDateTime.formatWithDuration(duration: Int): String {
    val format = LocalTime.Format {
        hour()
        char(':')
        minute()
    }
    val startTime = this.time.format(format)
    val endTime = this.toInstant(TimeZone.UTC)
        .plus(value = duration, unit = DateTimeUnit.MINUTE)
        .toLocalDateTime(TimeZone.UTC)
        .time.format(format)

    return "$startTime - $endTime"
}