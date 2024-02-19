package com.lucky.mycomposable.repository

import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.network.Constants
import com.lucky.mycomposable.network.RestApiBuilder
import com.lucky.mycomposable.utils.FormattedNetworkClientException
import com.lucky.mycomposable.utils.Result
import io.ktor.client.request.get

class PhotosRepositoryImpl(private val client:RestApiBuilder): PhotoRepository {

    override suspend fun photos(): Result<PhotoDTO> {
        return try {
            Result.Success(client.api.get(PhotoRepository.Endpoints.PHOTOS.url){

            })
        }catch (e: FormattedNetworkClientException){
            Result.Error(e.formattedErrorMessage)
        }
        catch (_:Exception){
            Result.Error("Server or Network Error")
        }
    }

}