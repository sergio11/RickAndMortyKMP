package sanchez.sergio.kmp_test.di.modules.episodes

import org.koin.core.qualifier.named
import org.koin.dsl.module
import sanchez.sergio.kmp_test.domain.interact.GetEpisodeByIdInteract
import sanchez.sergio.kmp_test.domain.interact.GetEpisodesInteract
import sanchez.sergio.kmp_test.persistence.api.episodes.EpisodeRepositoryImpl
import sanchez.sergio.kmp_test.persistence.api.episodes.IEpisodeRepository
import sanchez.sergio.kmp_test.persistence.network.mapper.episodes.EpisodeNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.repository.episodes.EpisodesNetworkRepositoryImpl
import sanchez.sergio.kmp_test.persistence.network.repository.episodes.IEpisodesNetworkRepository

/**
 * Episode Network Module
 */
private val episodesNetworkModule = module {
    single { EpisodeNetworkMapper()  }
    single<IEpisodesNetworkRepository> {
        EpisodesNetworkRepositoryImpl(get(), get(named("baseUrl")), get())
    }
}

/**
 * Episode Repository Module
 */
private val episodeRepositoryModule = module {
    single<IEpisodeRepository> { EpisodeRepositoryImpl(get()) }
}

/**
 * Episode Domain Module
 */
private val episodeDomainModule = module {
    factory { GetEpisodesInteract(get()) }
    factory { GetEpisodeByIdInteract(get()) }
}

/**
 * Episodes Module
 */
internal val episodesModule = arrayOf(
    episodesNetworkModule,
    episodeRepositoryModule,
    episodeDomainModule
)