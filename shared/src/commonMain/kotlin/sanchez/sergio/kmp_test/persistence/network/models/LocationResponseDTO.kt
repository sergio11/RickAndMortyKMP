package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class LocationsResponseDTO (
    val info: LocationsResponseInfoDTO,
    val results: List<LocationDTO>
)

@Serializable
data class LocationsResponseInfoDTO (
    val count: Long,
    val pages: Long,
    val next: String
)
