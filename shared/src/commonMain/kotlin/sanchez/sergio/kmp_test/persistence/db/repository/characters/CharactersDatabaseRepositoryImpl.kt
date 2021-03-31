package sanchez.sergio.kmp_test.persistence.db.repository.characters

import sanchez.sergio.kmp_test.domain.models.Character
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
    override suspend fun findAll(): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun save(model: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun save(modelList: List<Character>) {
        TODO("Not yet implemented")
    }
}