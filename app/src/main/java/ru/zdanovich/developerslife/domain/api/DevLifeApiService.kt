package ru.zdanovich.developerslife.domain.api

import ru.zdanovich.developerslife.domain.models.DevLifePost

interface DevLifeApiService {
    suspend fun getDevLifePosts(): List<DevLifePost>
}