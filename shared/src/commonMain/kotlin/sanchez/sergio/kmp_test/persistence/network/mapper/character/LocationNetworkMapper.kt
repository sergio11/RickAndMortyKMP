package sanchez.sergio.kmp_test.persistence.network.mapper.character

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.network.models.LocationDTO

/**
 * Location Network Mapper
 */
class LocationNetworkMapper {

    fun dtoToModel(dto: LocationDTO) = Location(
        id = dto.id,
        name = dto.name,
        type = dto.type,
        dimension = dto.dimension,
        residents = dto.residents,
        url = dto.url,
        created = dto.created
    )

    fun dtoToModel(dtoList: List<LocationDTO>) =
        dtoList.map { dtoToModel(it) }


    fun modelToDto(model: Location) = LocationDTO(
        id = model.id,
        name = model.name,
        type = model.type,
        dimension = model.dimension,
        residents = model.residents,
        url = model.url,
        created = model.created
    )

    fun modelToDto(modelList: List<Location>) =
        modelList.map { modelToDto(it) }

}