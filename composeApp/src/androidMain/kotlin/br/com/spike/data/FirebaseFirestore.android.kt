package br.com.spike.data

import br.com.spike.data.model.FirebaseObject
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlin.reflect.KClass

actual fun buildFirebaseFirestore(): FirebaseFirestore =
    AndroidFirebaseFirestore

private object AndroidFirebaseFirestore : FirebaseFirestore {
    override suspend fun <T> save(
        collection: String,
        document: String,
        data: T
    ) {
        Firebase.firestore
            .collection(collection)
            .document(document)
            .set(data as Any)
            .await()
    }

    override suspend fun <T> save(
        collection: String,
        data: T
    ) {
        Firebase.firestore
            .collection(collection)
            .add(data as Any)
            .await()
    }

    override suspend fun <T> update(collection: String, document: String, field: String, data: T) {
        val db = Firebase.firestore
        val docRef = db.collection(collection).document(document)
        db.runTransaction { transaction ->
            transaction.update(docRef, field , data as Any)
        }.await()
    }

    @Suppress("OVERRIDE_BY_INLINE")
    override suspend inline fun <T : Any> read(
        collection: String,
        document: String,
        kClass: KClass<T>
    ): FirebaseObject<T>? {
        val db = Firebase.firestore
        val docRef = db.collection(collection).document(document)
        val doc = docRef.get().await() ?: return null
        val data = doc.toObject(kClass.java) ?: return null
        return FirebaseObject(
            id = doc.id,
            data = data
        )
    }

    override suspend fun <T : Any> readWhereEqualTo(
        collection: String,
        field: String,
        value: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>> {
        val db = Firebase.firestore
        val query = db
            .collection(collection)
            .whereEqualTo(field, value)
        return query.get().await().map {
            FirebaseObject(
                id = it.id,
                data = it.toObject(kClass.java)
            )
        }
    }

    override suspend fun <T : Any> readWhereArrayContains(
        collection: String,
        field: String,
        value: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>> {
        val db = Firebase.firestore
        val query = db
            .collection(collection)
            .whereArrayContains(field, value)
        return query.get().await().map {
            FirebaseObject(
                id = it.id,
                data = it.toObject(kClass.java)
            )
        }
    }

    @Suppress("OVERRIDE_BY_INLINE")
    override suspend inline fun <T : Any> read(
        collection: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>> {
        val db = Firebase.firestore
        val reference = db.collection(collection)
        return reference.get().await().map {
            FirebaseObject(
                id = it.id,
                data = it.toObject(kClass.java)
            )
        }
    }

    @Suppress("OVERRIDE_BY_INLINE")
    override suspend inline fun <T : Any> read(
        collection: String,
        filters: List<FirestoreFilter>,
        kClass: KClass<T>
    ): List<FirebaseObject<T>> {
        val db = Firebase.firestore
        val reference = db.collection(collection)
            .run {
                var query: Query? = null
                filters.forEach { filter ->
                    query = when (filter.type) {
                        FirestoreFilterType.WhereArrayContains -> {
                            whereArrayContains(filter.field, filter.value)
                        }

                        FirestoreFilterType.WhereEqualTo -> {
                            whereEqualTo(filter.field, filter.value)
                        }

                        FirestoreFilterType.WhereGreaterThanOrEqualTo -> {
                            whereGreaterThanOrEqualTo(filter.field, filter.value)
                        }
                    }
                }
                query ?: this
            }
        return reference.get().await().map {
            FirebaseObject(
                id = it.id,
                data = it.toObject(kClass.java)
            )
        }
    }
}