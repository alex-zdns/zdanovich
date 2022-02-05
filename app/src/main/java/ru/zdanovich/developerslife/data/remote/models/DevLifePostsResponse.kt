package ru.zdanovich.developerslife.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DevLifePostsResponse (
    @SerialName("result")
    val posts: List<DevLifePostResponse?>?,

    @SerialName("totalCount")
    val totalCount: Int?
)