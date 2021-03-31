package sanchez.sergio.kmp_test.persistence.db.mapper

import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmptest.EpisodeEntity

/**
 * Episodes DB Mapper
 */
class EpisodesDBMapper {

    /**
     * Entity to model
     * @param entity
     * @return episode
     */
    fun entityToModel(entity: EpisodeEntity) = Episode(
        id = entity.id,
        name = entity.name,
        airDate = entity.airDate,
        episode = entity.episode,
        characters = entity.characters,
        url = entity.url,
        created = entity.created
    )

    /**
     * Entity to model
     * @param entityList
     */
    fun entityToModel(entityList: List<EpisodeEntity>) =
        entityList.map { entityToModel(it) }

    /**
     * Model to entity
     * @param model
     */
    fun modelToEntity(model: Episode) = EpisodeEntity(
        id = model.id,
        name = model.name,
        airDate = model.airDate,
        episode = model.episode,
        characters = model.characters,
        url = model.url,
        created = model.created
    )

    /**
     * Model to entity
     * @param modelList
     */
    fun modelToEntity(modelList: List<Episode>) =
        modelList.map { modelToEntity(it) }

}