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
        residents = dto.residents.size,
        url = dto.url,
        created = dto.created
    )

    fun dtoToModel(dtoList: List<LocationDTO>) =
        dtoList.map { dtoToModel(it) }

}