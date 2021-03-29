package sanchez.sergio.kmp_test.persistence.network.mapper.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.network.models.EpisodeDTO

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
        characters = dto.characters,
        url = dto.url,
        created = dto.created
    )

    fun dtoToModel(dtoList: List<EpisodeDTO>) =
        dtoList.map { dtoToModel(it) }

    /**
     * Model To DTO
     * @param model
     */
    fun modelToDto(model: Episode) = EpisodeDTO(
        id = model.id,
        name = model.name,
        airDate = model.airDate,
        episode = model.episode,
        characters = model.characters,
        url = model.url,
        created = model.created
    )

    fun modelToDto(modelList: List<Episode>) =
        modelList.map { modelToDto(it) }

}