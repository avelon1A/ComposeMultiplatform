package org.aman.coding.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json


fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true;
                    isLenient = true
                }
            )
        }


    }
}