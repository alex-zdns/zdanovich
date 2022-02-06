package ru.zdanovich.developerslife.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PageInfoResponse<T> (
    @SerialName("result")
    val items: List<T>?,

    @SerialName("totalCount")
    val totalCount: Int?
)