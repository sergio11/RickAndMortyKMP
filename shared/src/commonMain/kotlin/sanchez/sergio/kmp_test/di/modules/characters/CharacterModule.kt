package sanchez.sergio.kmp_test.di.modules.characters

import org.koin.dsl.module
import sanchez.sergio.kmp_test.domain.interact.GetCharactersInteract
import sanchez.sergio.kmp_test.persistence.api.character.CharacterRepositoryImpl
import sanchez.sergio.kmp_test.persistence.api.character.ICharacterRepository
import sanchez.sergio.kmp_test.persistence.network.repository.character.CharacterNetworkRepositoryImpl
import sanchez.sergio.kmp_test.persistence.network.repository.character.ICharacterNetworkRepository

internal val charactersNetworkModule = module {
    single<ICharacterNetworkRepository> {
        CharacterNetworkRepositoryImpl(get())
    }
}

internal val characterRepositoryModule = module {
    single<ICharacterRepository> {
        CharacterRepositoryImpl(get())
    }
}

internal val characterDomainModule = module {
    factory { GetCharactersInteract(get()) }
}

internal val charactersModule = module {
    charactersNetworkModule
    characterRepositoryModule
    characterDomainModule
}


