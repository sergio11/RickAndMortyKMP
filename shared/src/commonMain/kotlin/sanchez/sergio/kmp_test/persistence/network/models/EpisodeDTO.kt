package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class EpisodeDTO (
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode")
    val episode: String,
    @SerialName("characters")
    val characters: List<String>,
    @SerialName("url")
    val url: String,
    @SerialName("created")
    val created: String
)
