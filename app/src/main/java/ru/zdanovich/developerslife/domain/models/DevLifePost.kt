package ru.zdanovich.developerslife.domain.models


data class DevLifePost(
    val id: Int,
    val description: String,
    val gifUrl: String,
    val previewURL: String?,
)