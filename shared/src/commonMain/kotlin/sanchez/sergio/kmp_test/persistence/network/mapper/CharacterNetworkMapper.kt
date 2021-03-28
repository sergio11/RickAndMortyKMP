package sanchez.sergio.kmp_test.persistence.network.mapper

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.CharacterStatusEnum
import sanchez.sergio.kmp_test.persistence.network.models.CharacterDTO

/**
 * Character Network Mapper
 */
class CharacterNetworkMapper(
    private val locationNetworkMapper: LocationNetworkMapper
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
            gender = dto.gender,
            origin = locationNetworkMapper.dtoToModel(dto.origin),
            location = locationNetworkMapper.dtoToModel(dto.location),
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
            gender = model.gender,
            origin = locationNetworkMapper.modelToDto(model.origin),
            location = locationNetworkMapper.modelToDto(model.location),
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