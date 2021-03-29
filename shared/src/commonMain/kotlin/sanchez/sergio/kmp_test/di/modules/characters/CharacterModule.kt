package sanchez.sergio.kmp_test.di.modules.characters

import org.koin.core.qualifier.named
import org.koin.dsl.module
import sanchez.sergio.kmp_test.domain.interact.GetCharacterByIdInteract
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.persistence.api.character.CharacterRepositoryImpl
import sanchez.sergio.kmp_test.persistence.api.character.ICharacterRepository
import sanchez.sergio.kmp_test.persistence.network.mapper.CharacterNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.mapper.LocationNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.repository.character.CharacterNetworkRepositoryImpl
import sanchez.sergio.kmp_test.persistence.network.repository.character.ICharacterNetworkRepository

/**
 * Character Network Module
 */
internal val charactersNetworkModule = module {
    single { LocationNetworkMapper() }
    single { CharacterNetworkMapper(get())  }
    single<ICharacterNetworkRepository> {
        CharacterNetworkRepositoryImpl(get(), get(named("baseUrl")), get())
    }
}

/**
 * Character Repository Module
 */
internal val characterRepositoryModule = module {
    single<ICharacterRepository> { CharacterRepositoryImpl(get()) }
}

/**
 * Character Domain Module
 */
internal val characterDomainModule = module {
    factory { GetCharactersInteract(get()) }
    factory { GetCharacterByIdInteract(get()) }
}

/**
 * Characters Module
 */
internal val charactersModule = arrayOf(
    charactersNetworkModule,
    characterRepositoryModule,
    characterDomainModule
)