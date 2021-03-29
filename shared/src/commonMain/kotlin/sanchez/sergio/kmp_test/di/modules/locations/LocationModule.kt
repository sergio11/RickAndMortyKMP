package sanchez.sergio.kmp_test.di.modules.locations

import org.koin.core.qualifier.named
import org.koin.dsl.module
import sanchez.sergio.kmp_test.domain.interact.GetLocationByIdInteract
import sanchez.sergio.kmp_test.domain.interact.GetLocationsInteract
import sanchez.sergio.kmp_test.persistence.api.location.ILocationRepository
import sanchez.sergio.kmp_test.persistence.api.location.LocationRepositoryImpl
import sanchez.sergio.kmp_test.persistence.network.mapper.character.LocationNetworkMapper
import sanchez.sergio.kmp_test.persistence.network.repository.location.ILocationNetworkRepository
import sanchez.sergio.kmp_test.persistence.network.repository.location.LocationNetworkRepositoryImpl

/**
 * Location Network Module
 */
private val locationsNetworkModule = module {
    single { LocationNetworkMapper()  }
    single<ILocationNetworkRepository> {
        LocationNetworkRepositoryImpl(get(), get(named("baseUrl")), get())
    }
}

/**
 * Locations Repository Module
 */
private val locationsRepositoryModule = module {
    single<ILocationRepository> { LocationRepositoryImpl(get()) }
}

/**
 * Location Domain Module
 */
private val locationsDomainModule = module {
    factory { GetLocationsInteract(get()) }
    factory { GetLocationByIdInteract(get()) }
}

/**
 * Locations Module
 */
internal val locationsModule = arrayOf(
    locationsNetworkModule,
    locationsRepositoryModule,
    locationsDomainModule
)