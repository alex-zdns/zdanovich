package ru.zdanovich.developerslife.domain.api

import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.SectionType

interface DevLifeApiService {
    suspend fun getDevLifePosts(sectionType: SectionType): List<DevLifePost>
}