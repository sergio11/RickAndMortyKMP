package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class EpisodesResponseDTO (
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val results: List<EpisodeDTO>
)

@Serializable
data class InfoDTO (
    @SerialName("count")
    val count: Long,
    @SerialName("pages")
    val pages: Long,
    @SerialName("next")
    val next: String
)
