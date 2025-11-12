package br.com.spike.data

import com.google.firebase.Firebase
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

    @Suppress("OVERRIDE_BY_INLINE")
    override suspend inline fun <T : Any> read(
        collection: String,
        kClass: KClass<T>
    ): List<FirebaseObject<T>> {
        val db = Firebase.firestore
        return db.collection(collection).get().await().map {
            FirebaseObject(
                id = it.id,
                data = it.toObject(kClass.java)
            )
        }
    }
}