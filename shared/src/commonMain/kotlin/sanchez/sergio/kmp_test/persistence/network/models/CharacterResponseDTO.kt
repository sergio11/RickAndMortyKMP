package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class CharactersResponse (
    val info: Info,
    val results: List<CharacterDTO>
)

@Serializable
data class Info (
    val count: Long,
    val pages: Long,
    val next: String,
)
