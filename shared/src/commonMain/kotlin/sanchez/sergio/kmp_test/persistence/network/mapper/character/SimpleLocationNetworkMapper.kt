package sanchez.sergio.kmp_test.persistence.network.mapper.character

import sanchez.sergio.kmp_test.domain.models.SimpleLocation
import sanchez.sergio.kmp_test.persistence.network.models.SimpleLocationDTO

class SimpleLocationNetworkMapper {

    /**
     * DTO to model
     * @param dtoSimple
     */
    fun dtoToModel(dtoSimple: SimpleLocationDTO) =
        SimpleLocation(
            name = dtoSimple.name,
            url = dtoSimple.url
        )

    /**
     * Model to DTO
     * @param model
     */
    fun modelToDto(model: SimpleLocation) =
        SimpleLocationDTO(
            name = model.name,
            url = model.url
        )

}