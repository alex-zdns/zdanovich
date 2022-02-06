package ru.zdanovich.developerslife.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevLifePostResponse(
    @SerialName("id")
    val id: Int?,

    @SerialName("description")
    val description: String?,

    @SerialName("gifURL")
    val gifUrl: String?
)