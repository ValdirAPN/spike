package br.com.spike.data

data class FirebaseObject<T>(
    val id: String,
    val data: T,
)