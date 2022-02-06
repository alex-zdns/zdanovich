package ru.zdanovich.developerslife.domain.api

import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.PageInfo
import ru.zdanovich.developerslife.domain.models.SectionType

interface DevLifeApiService {
    suspend fun getDevLifePosts(
        sectionType: SectionType,
        page: Int = 0,
        pageSize: Int = 10
    ): PageInfo<DevLifePost>
}