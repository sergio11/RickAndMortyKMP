package sanchez.sergio.kmp_test.di.modules

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/**
 * Network Module
 */

internal val networkModule = module {

    single {
        HttpClient(CIO) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
            }
            // Logging
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            // Configure OkHTTP Interceptors
            engine {
                // Endpoint specific settings.
                endpoint {
                    // Maximum number of requests for a specific endpoint route.
                    maxConnectionsPerRoute = 100
                    // Max size of scheduled requests per connection(pipeline queue size).
                    pipelineMaxSize = 20
                    // Max number of milliseconds to keep iddle connection alive.
                    keepAliveTime = 5000
                    // Number of milliseconds to wait trying to connect to the server.
                    connectTimeout = 5000
                    // Maximum number of attempts for retrying a connection.
                    connectAttempts = 5
                }
            }
        }
    }


}