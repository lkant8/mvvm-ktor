package com.lucky.mycomposable.repository

import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.network.RestApiBuilder
import com.lucky.mycomposable.utils.FormattedNetworkClientException
import com.lucky.mycomposable.utils.Result
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class PhotosRepositoryImpl(private val client:RestApiBuilder): PhotoRepository {

    override suspend fun photos(): Result<List<PhotoDTO>> {
        return try {
            Result.Success(client.api.get(PhotoRepository.Endpoints.PHOTOS.url){
                header(HttpHeaders.ContentType,ContentType.Application.Json)
            })
        } catch (exception: FormattedNetworkClientException) {
            Result.Error(exception.formattedErrorMessage)
        } catch (exception: Exception) {
            println(exception)
            Result.Error("Server or network error")
        }
    }

}