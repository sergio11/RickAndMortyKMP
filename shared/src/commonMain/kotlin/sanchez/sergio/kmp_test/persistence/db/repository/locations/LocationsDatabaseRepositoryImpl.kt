package sanchez.sergio.kmp_test.persistence.db.repository.locations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sergio.kmp_test.domain.models.Location
import sanchez.sergio.kmp_test.persistence.db.exception.DBErrorException
import sanchez.sergio.kmp_test.persistence.db.exception.DBNoResultException
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

    /**
     * Find all
     */
    override suspend fun findAll(): List<Location> = withContext(Dispatchers.Default) {
        try {
            val resultList = locationsQueries.selectAll().executeAsList()
            if(resultList.isEmpty())
                throw DBNoResultException()
            resultList.map {
                locationsDBMapper.entityToModel(it)
            }
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }

    /**
     * Delete all
     */
    override suspend fun deleteAll() = withContext(Dispatchers.Default){
        try {
            locationsQueries.deleteAll()
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }

    /**
     * Save
     * @param model
     */
    override suspend fun save(model: Location) = withContext(Dispatchers.Default){
        try {
            locationsQueries.insertItem(
                id = model.id,
                name = model.name,
                type = model.type,
                dimension = model.dimension,
                residents = model.residents,
                url = model.url,
                created = model.created
            )
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }

    /**
     * Save
     * @param modelList
     */
    override suspend fun save(modelList: List<Location>) = withContext(Dispatchers.Default){
        try {
            locationsQueries.transaction {
                modelList.forEach { model ->
                    locationsQueries.insertItem(
                        id = model.id,
                        name = model.name,
                        type = model.type,
                        dimension = model.dimension,
                        residents = model.residents,
                        url = model.url,
                        created = model.created
                    )
                }
            }
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }
}