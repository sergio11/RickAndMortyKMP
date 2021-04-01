package sanchez.sergio.kmp_test.persistence.db.repository.core

import sanchez.sergio.kmp_test.persistence.db.exception.DBErrorException
import sanchez.sergio.kmp_test.persistence.db.exception.DBNoResultException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Database Repository
 */
interface IDatabaseRepository<T> {

    /**
     * Find all
     */
    @Throws(DBNoResultException::class, DBErrorException::class, CancellationException::class)
    suspend fun findAll(): List<T>

    /**
     * Delete All
     */
    @Throws(DBErrorException::class, CancellationException::class)
    suspend fun deleteAll()

    /**
     * Save model
     * @param model
     */
    @Throws(DBErrorException::class, CancellationException::class)
    suspend fun save(model: T)

    /**
     * Save model list
     * @param modelList
     */
    @Throws(DBErrorException::class, CancellationException::class)
    suspend fun save(modelList: List<T>)
}