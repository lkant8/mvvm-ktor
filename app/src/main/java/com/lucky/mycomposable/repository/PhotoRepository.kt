package com.lucky.mycomposable.repository

import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.utils.Result

interface PhotoRepository {

    suspend fun photos():Result<PhotoDTO>

    sealed class Endpoints(val url:String){
        object PHOTOS: Endpoints("/photos")
        object COMMENTS: Endpoints("/comments")
    }
}