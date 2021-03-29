package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class CharacterDTO (
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("status")
    val status: String,
    @SerialName("species")
    val species: String,
    @SerialName("type")
    val type: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("origin")
    val origin: SimpleLocationDTO,
    @SerialName("location")
    val simpleLocation: SimpleLocationDTO,
    @SerialName("image")
    val image: String,
    @SerialName("episode")
    val episode: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String
)

@Serializable
data class SimpleLocationDTO (
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
