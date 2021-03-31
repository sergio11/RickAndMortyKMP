package sanchez.sergio.kmp_test.persistence.db.repository.episodes

import sanchez.sergio.kmp_test.domain.models.Episode
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

    override suspend fun findAll(): List<Episode> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun save(model: Episode) {
        TODO("Not yet implemented")
    }

    override suspend fun save(modelList: List<Episode>) {
        TODO("Not yet implemented")
    }
}