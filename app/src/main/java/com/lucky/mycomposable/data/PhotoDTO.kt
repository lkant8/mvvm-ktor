package com.lucky.mycomposable.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDTO(
    @SerialName("albumId")
    val albumId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
)