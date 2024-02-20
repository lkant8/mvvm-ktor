package com.lucky.mycomposable.repository

import com.lucky.mycomposable.data.PhotoDTO
import com.lucky.mycomposable.network.Constants
import com.lucky.mycomposable.network.Constants.BASE
import com.lucky.mycomposable.utils.Result

interface PhotoRepository {

    suspend fun photos():Result<List<PhotoDTO>>

    sealed class Endpoints(val url:String){
        object PHOTOS: Endpoints("$BASE/photos")
        object COMMENTS: Endpoints("$BASE/comments")
    }
}