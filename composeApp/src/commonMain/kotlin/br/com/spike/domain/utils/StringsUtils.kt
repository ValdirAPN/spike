package br.com.spike.domain.utils

fun String.isDigitsOnly(): Boolean = all(Char::isDigit)