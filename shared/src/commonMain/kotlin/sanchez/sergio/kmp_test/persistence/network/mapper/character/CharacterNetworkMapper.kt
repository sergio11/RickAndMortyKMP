package sanchez.sergio.kmp_test.persistence.network.mapper.character

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.CharacterGenderEnum
import sanchez.sergio.kmp_test.domain.models.CharacterStatusEnum
import sanchez.sergio.kmp_test.persistence.network.models.CharacterDTO

/**
 * Character Network Mapper
 */
class CharacterNetworkMapper(
    private val simpleLocationNetworkMapper: SimpleLocationNetworkMapper
) {

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
            origin = simpleLocationNetworkMapper.dtoToModel(dto.origin),
            simpleLocation = simpleLocationNetworkMapper.dtoToModel(dto.simpleLocation),
            image = dto.image,
            episode = dto.episode,
            url = dto.url,
            created = dto.created
        )

    /**
     * DTO to Model
     * @param dtoList
     */
    fun dtoToModel(dtoList: List<CharacterDTO>) =
        dtoList.map { dtoToModel(it) }

    /**
     * Model to DTO
     * @param model
     */
    fun modelToDto(model: Character) =
        CharacterDTO(
            id = model.id,
            name = model.name,
            status = model.status.name,
            species = model.species,
            type = model.type,
            gender = model.gender.name,
            origin = simpleLocationNetworkMapper.modelToDto(model.origin),
            simpleLocation = simpleLocationNetworkMapper.modelToDto(model.simpleLocation),
            image = model.image,
            episode = model.episode,
            url = model.url,
            created = model.created
        )

    /**
     * Model to DTO
     * @param modelList
     */
    fun modelToDto(modelList: List<Character>) =
        modelList.map { modelToDto(it) }
}