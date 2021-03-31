package sanchez.sergio.kmp_test.persistence.db.repository.core

/**
 * Database Repository
 */
interface IDatabaseRepository<T> {

    /**
     * Find all
     */
    suspend fun findAll(): List<T>

    /**
     * Delete All
     */
    suspend fun deleteAll()

    /**
     * Save model
     * @param model
     */
    suspend fun save(model: T)

    /**
     * Save model list
     * @param modelList
     */
    suspend fun save(modelList: List<T>)
}