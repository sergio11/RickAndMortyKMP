package sanchez.sergio.kmp_test.persistence.db.repository.episodes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sergio.kmp_test.domain.models.Episode
import sanchez.sergio.kmp_test.persistence.db.exception.DBErrorException
import sanchez.sergio.kmp_test.persistence.db.exception.DBNoResultException
import sanchez.sergio.kmp_test.persistence.db.mapper.EpisodesDBMapper
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmptest.EpisodeQueries

/**
 * Episodes Database Repository
 * @param episodeQueries
 * @param episodesDBMapper
 */
class EpisodesDatabaseRepositoryImpl(
    private val episodeQueries: EpisodeQueries,
    private val episodesDBMapper: EpisodesDBMapper
): IDatabaseRepository<Episode> {

    /**
     * Find All
     */
    override suspend fun findAll(): List<Episode> = withContext(Dispatchers.Default) {
        try {
            val resultList = episodeQueries.selectAll().executeAsList()
            if(resultList.isEmpty())
                throw DBNoResultException()
            resultList.map {
                episodesDBMapper.entityToModel(it)
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
            episodeQueries.deleteAll()
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }

    /**
     * Save
     * @param model
     */
    override suspend fun save(model: Episode) = withContext(Dispatchers.Default){
        try {
            episodeQueries.insertItem(
                id = model.id,
                name = model.name,
                airDate = model.airDate,
                episode = model.episode,
                characters = model.characters,
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
    override suspend fun save(modelList: List<Episode>) = withContext(Dispatchers.Default){
        try {
            episodeQueries.transaction {
                modelList.forEach { model ->
                    episodeQueries.insertItem(
                        id = model.id,
                        name = model.name,
                        airDate = model.airDate,
                        episode = model.episode,
                        characters = model.characters,
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