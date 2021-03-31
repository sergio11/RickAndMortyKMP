package sanchez.sergio.kmp_test.di.modules.characters

import org.koin.core.qualifier.named
import org.koin.dsl.module
import sanchez.sergio.kmp_test.db.KmpArchDb
import sanchez.sergio.kmp_test.domain.interact.GetCharacterByIdInteract
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.domain.models.Character
import sanchez.sergio.kmp_test.persistence.api.character.CharacterRepositoryImpl
import sanchez.sergio.kmp_test.persistence.api.character.ICharacterRepository
import sanchez.sergio.kmp_test.persistence.db.mapper.CharactersDBMapper
import sanchez.sergio.kmp_test.persistence.db.repository.characters.CharactersDatabaseRepositoryImpl
import sanchez.sergio.kmp_test.persistence.db.repository.core.IDatabaseRepository
import sanchez.sergio.kmp_test.persistence.network.mapper.character.CharacterNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.repository.character.CharacterNetworkRepositoryImpl
import sanchez.sergio.kmp_test.persistence.network.repository.character.ICharacterNetworkRepository

/**
 * Character Network Module
 */
private val charactersNetworkModule = module {
    single { CharacterNetworkMapper()  }
    single<ICharacterNetworkRepository> {
        CharacterNetworkRepositoryImpl(get(), get(named("baseUrl")), get())
    }
}

/**
 * Character DB Module
 */
private val characterDBModule = module {
    single { CharactersDBMapper() }
    single {
        val database = get(KmpArchDb::class, null) as KmpArchDb
        database.characterQueries
    }
    single<IDatabaseRepository<Character>>(named("CharactersDatabaseRepository")) {
        CharactersDatabaseRepositoryImpl(get(), get())
    }
}

/**
 * Character Repository Module
 */
private val characterRepositoryModule = module {
    single<ICharacterRepository> { CharacterRepositoryImpl(get(), get(named("CharactersDatabaseRepository"))) }
}

/**
 * Character Domain Module
 */
private val characterDomainModule = module {
    factory { GetCharactersInteract(get()) }
    factory { GetCharacterByIdInteract(get()) }
}

/**
 * Characters Module
 */
internal val charactersModule = arrayOf(
    charactersNetworkModule,
    characterDBModule,
    characterRepositoryModule,
    characterDomainModule
)