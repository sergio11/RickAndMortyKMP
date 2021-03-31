package sanchez.sergio.kmp_test.persistence.db.repository.locations

import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.db.mapper.LocationsDBMapper
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmptest.LocationQueries

/**
 * Locations Database Repository impl
 * @param locationsQueries
 * @param locationsDBMapper
 */
class LocationsDatabaseRepositoryImpl(
    private val locationsQueries: LocationQueries,
    private val locationsDBMapper: LocationsDBMapper
): IDatabaseRepository<Location> {
    override suspend fun findAll(): List<Location> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun save(model: Location) {
        TODO("Not yet implemented")
    }

    override suspend fun save(modelList: List<Location>) {
        TODO("Not yet implemented")
    }
}