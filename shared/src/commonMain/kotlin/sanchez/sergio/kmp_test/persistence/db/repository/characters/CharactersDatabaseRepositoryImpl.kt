package sanchez.sergio.kmp_test.persistence.db.repository.characters

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.db.exception.DBErrorException
import sanchez.sergio.kmp_test.persistence.db.exception.DBNoResultException
import sanchez.sergio.kmp_test.persistence.db.mapper.CharactersDBMapper
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmptest.CharacterQueries

/**
 * Characters Database Repository
 * @param characterQueries
 * @param charactersDBMapper
 */
class CharactersDatabaseRepositoryImpl(
    private val characterQueries: CharacterQueries,
    private val charactersDBMapper: CharactersDBMapper
): IDatabaseRepository<Character> {

    /**
     * Find all
     */
    override suspend fun findAll(): List<Character> = withContext(Dispatchers.Default) {
        try {
            val resultList = characterQueries.selectAll().executeAsList()
            if(resultList.isEmpty())
                throw DBNoResultException()
            resultList.map {
                charactersDBMapper.entityToModel(it)
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
            characterQueries.deleteAll()
        } catch (ex: Exception) {
            throw DBErrorException("DB Error occurred", ex)
        }
    }

    /**
     * Save
     * @param model
     */
    override suspend fun save(model: Character) = withContext(Dispatchers.Default){
        try {
            characterQueries.insertItem(
                id = model.id,
                name = model.name,
                status = model.status.name,
                species = model.species,
                type = model.type,
                gender = model.gender.name,
                origin = model.origin,
                location = model.location,
                image = model.image,
                firstEpisode = model.firstEpisode,
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
    override suspend fun save(modelList: List<Character>) {
        try {
            characterQueries.transaction {
                modelList.forEach { model ->
                    characterQueries.insertItem(
                        id = model.id,
                        name = model.name,
                        status = model.status.name,
                        species = model.species,
                        type = model.type,
                        gender = model.gender.name,
                        origin = model.origin,
                        location = model.location,
                        image = model.image,
                        firstEpisode = model.firstEpisode,
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