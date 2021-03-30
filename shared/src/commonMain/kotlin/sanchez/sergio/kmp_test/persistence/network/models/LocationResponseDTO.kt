package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.*

@Serializable
data class LocationsResponseDTO (
    @SerialName("info")
    val info: PageDataDTO,
    @SerialName("results")
    val results: List<LocationDTO>
)
