package br.com.spike.domain.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames

fun LocalDate.toDayOfWeekDayOfMonthAndMonthName(
    daysOfWeekNames: DayOfWeekNames,
    monthNames: MonthNames,
): String {
    val formatter = LocalDate.Format {
        dayOfWeek(names = daysOfWeekNames)
        chars(", ")
        day()
        chars(" de ")
        monthName(names = monthNames)
    }

    return formatter.format(this)
}