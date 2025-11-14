package br.com.spike.data.model

data class FirebaseObject<T>(
    val id: String,
    val data: T,
)