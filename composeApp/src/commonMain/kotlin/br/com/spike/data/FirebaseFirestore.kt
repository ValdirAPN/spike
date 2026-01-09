package br.com.spike.data

import br.com.spike.data.model.FirebaseObject
import kotlin.reflect.KClass

expect fun buildFirebaseFirestore(): FirebaseFirestore

interface FirebaseFirestore {

    suspend fun <T> save(
        collection: String,
        document: String,
        data: T
    )

    suspend fun <T> save(
        collection: String,
        data: T,
    )

    suspend fun <T: Any> read(
        collection: String,
        document: String,
        kClass: KClass<T>
    ): FirebaseObject<T>?

    suspend fun <T : Any> readWhereEqualTo(
        collection: String,
        field: String,
        value: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>>

    suspend fun <T : Any> readWhereArrayContains(
        collection: String,
        field: String,
        value: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>>

    suspend fun <T : Any> read(
        collection: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>>

    suspend fun <T : Any> read(
        collection: String,
        filters: List<FirestoreFilter>,
        kClass: KClass<T>
    ): List<FirebaseObject<T>>

    suspend fun <T> update(
        collection: String,
        document: String,
        field: String,
        data: T
    )
}