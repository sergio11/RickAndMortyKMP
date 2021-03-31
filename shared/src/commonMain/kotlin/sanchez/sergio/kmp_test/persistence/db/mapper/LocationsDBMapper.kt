package sanchez.sergio.kmp_test.persistence.db.mapper

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmptest.LocationEntity

/**
 * Locations DB Mapper
 */
class LocationsDBMapper {

    /**
     * Entity to model
     * @param entity
     * @return location
     */
    fun entityToModel(entity: LocationEntity) = Location(
        id = entity.id,
        name = entity.name,
        type = entity.type,
        dimension = entity.dimension,
        residents = entity.residents,
        url = entity.url,
        created = entity.created
    )

    /**
     * Entity to model
     * @param entityList
     */
    fun entityToModel(entityList: List<LocationEntity>) =
        entityList.map { entityToModel(it) }

    /**
     * Model to entity
     * @param model
     */
    fun modelToEntity(model: Location) = LocationEntity(
        id = model.id,
        name = model.name,
        type = model.type,
        dimension = model.dimension,
        residents = model.residents,
        url = model.url,
        created = model.created
    )

    /**
     * Model to entity
     * @param modelList
     */
    fun modelToEntity(modelList: List<Location>) =
        modelList.map { modelToEntity(it) }

}