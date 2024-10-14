package com.fetch.rewards.data.di

import com.fetch.rewards.util.Logger
import com.fetch.rewards.util.getLoggerWithTag
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import io.ktor.client.features.logging.Logger as KtorLogger

private const val TIME_OUT = 6000

internal fun Module.networkModule() {
    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
    single {
        initHttpClient(json = get<Json>(), log = getLoggerWithTag("HttpClient"))
    }
}

private fun initHttpClient(
    host: String = "fetch-hiring.s3.amazonaws.com",
    json: Json,
    log: Logger
) = HttpClient(Android) {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            this.host = host
        }
        contentType(ContentType.Application.Json)
    }
    expectSuccess = true
    install(JsonFeature) {
        KotlinxSerializer(json)

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }

        //Logging
        install(Logging) {
            logger = object : KtorLogger {
                override fun log(message: String) {
                    log.debug(message)
                }
            }
        }

        //Http Response
        install(ResponseObserver) {
            onResponse { response -> log.debug(response.status.value.toString()) }
        }
    }
}