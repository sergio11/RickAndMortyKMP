package sanchez.sergio.kmp_test.persistence.network.mapper.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.CharacterGenderEnum
import sanchez.sergio.kmp_test.domain.models.CharacterStatusEnum
import sanchez.sergio.kmp_test.persistence.network.models.CharacterDTO

/**
 * Character Network Mapper
 */
class CharacterNetworkMapper {

    /**
     * DTO to Model
     * @param dto
     */
    fun dtoToModel(dto: CharacterDTO) =
        Character(
            id = dto.id,
            name = dto.name,
            status = CharacterStatusEnum.valueOf(dto.status),
            species = dto.species,
            type = dto.type,
            gender = CharacterGenderEnum.valueOf(dto.gender),
            origin = dto.origin.name,
            location = dto.location.name,
            image = dto.image,
            firstEpisode = dto.episode.firstOrNull(),
            url = dto.url,
            created = dto.created
        )

    /**
     * DTO to Model
     * @param dtoList
     */
    fun dtoToModel(dtoList: List<CharacterDTO>) =
        dtoList.map { dtoToModel(it) }
}