package sanchez.sergio.kmp_test.persistence.network.mapper.character

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.network.models.LocationDTO

class LocationNetworkMapper {

    /**
     * DTO to model
     * @param dto
     */
    fun dtoToModel(dto: LocationDTO) =
        Location(
            name = dto.name,
            url = dto.url
        )

    /**
     * Model to DTO
     * @param model
     */
    fun modelToDto(model: Location) =
        LocationDTO(
            name = model.name,
            url = model.url
        )

}