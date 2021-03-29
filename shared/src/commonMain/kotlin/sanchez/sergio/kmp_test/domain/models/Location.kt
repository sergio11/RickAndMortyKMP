package sanchez.sergio.kmp_test.domain.models

data class Location(
    val id: Long,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)
