package br.com.spike.data

enum class FirestoreFilterType {
    WhereArrayContains,
    WhereEqualTo,
    WhereGreaterThanOrEqualTo
}

data class FirestoreFilter(
    val type: FirestoreFilterType,
    val field: String,
    val value: Any,
)