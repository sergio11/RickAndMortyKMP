package sanchez.sergio.kmp_test.persistence.db.mapper

import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.domain.models.CharacterGenderEnum
import sanchez.sergio.kmp_test.domain.models.CharacterStatusEnum
import sanchez.sergio.kmptest.CharacterEntity

/**
 * Characters DB Mapper
 */
class CharactersDBMapper {

    /**
     * Entity to model
     * @param entity
     * @return character
     */
    fun entityToModel(entity: CharacterEntity) = Character(
        id = entity.id,
        name = entity.name,
        status = CharacterStatusEnum.valueOf(entity.status),
        species = entity.species,
        type = entity.type,
        gender = CharacterGenderEnum.valueOf(entity.gender),
        origin = entity.origin,
        location = entity.location,
        image = entity.image,
        firstEpisode = entity.firstEpisode,
        url = entity.url,
        created = entity.created
    )

    /**
     * Entity to model
     * @param entityList
     */
    fun entityToModel(entityList: List<CharacterEntity>) =
        entityList.map { entityToModel(it) }

    /**
     * Model to entity
     * @param model
     */
    fun modelToEntity(model: Character) = CharacterEntity(
        id = model.id,
        name = model.name,
        status = model.status.name,
        species = model.species,
        type = model.type,
        gender = model.gender.name,
        origin = model.origin,
        location = model.location,
        image = model.image,
        firstEpisode = model.firstEpisode,
        url = model.url,
        created = model.created
    )

    /**
     * Model to entity
     * @param modelList
     */
    fun modelToEntity(modelList: List<Character>) =
        modelList.map { modelToEntity(it) }

}