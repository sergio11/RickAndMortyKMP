package sanchez.sergio.kmp_test.persistence.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageDataDTO(
    @SerialName("count")
    val count: Long,
    @SerialName("pages")
    val pages: Long,
    @SerialName("next")
    val next: String?,
)
