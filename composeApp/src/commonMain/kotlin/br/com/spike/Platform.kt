package br.com.spike

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform