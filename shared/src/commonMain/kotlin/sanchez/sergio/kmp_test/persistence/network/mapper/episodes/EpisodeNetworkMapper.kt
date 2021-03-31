package sanchez.sergio.kmp_test.persistence.network.mapper.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.network.models.EpisodeDTO
import kotlin.math.sign

/**
 * Episode Network Mapper
 */
class EpisodeNetworkMapper {

    /**
     * DTO to Model
     * @param dto
     */
    fun dtoToModel(dto: EpisodeDTO) = Episode(
        id = dto.id,
        name = dto.name,
        airDate = dto.airDate,
        episode = dto.episode,
        characters = dto.characters.size,
        url = dto.url,
        created = dto.created
    )

    fun dtoToModel(dtoList: List<EpisodeDTO>) =
        dtoList.map { dtoToModel(it) }


}