package com.lucky.mycomposable.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

object Constants{
    const val BASE ="https://jsonplaceholder.typicode.com/"

}

class RestApiBuilder(private val s:String): ApiBuilder() {

    val api = HttpClient(Android){

        install(JsonFeature){
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys =true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging){
            logger = object :Logger{
                override fun log(message: String) {
                    Log.v(TAG, "log: Ktor=>  $message")
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver){
            onResponse {
                Log.v(TAG, "Kotr response : ${it.status.value}")
            }
        }

        // handle exception


        install(DefaultRequest){
            header(HttpHeaders.ContentType,ContentType.Application.Json)
        }

    }

}

private const val TIME_OUT = 60_000
private const val TAG = "config"
/*
val ktorHttpClient = HttpClient(Android){

    install(JsonFeature){
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys =true
        })

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    install(Logging){
        logger = object :Logger{
            override fun log(message: String) {
                Log.v(TAG, "log: Ktor=>  $message")
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver){
        onResponse {
            Log.v(TAG, "Kotr response : ${it.status.value}")
        }
    }

    // handle exception


    install(DefaultRequest){
        header(HttpHeaders.ContentType,ContentType.Application.Json)
    }

}*/
