package ru.zdanovich.developerslife.domain.repositoty

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.domain.paging.DevLifePageSource
import ru.zdanovich.developerslife.domain.paging.createPager
import javax.inject.Inject

class DevLifePostRepository @Inject constructor(
    private val api: DevLifeApiService
) {
    fun getPosts(
        sectionType: SectionType,
    ): Flow<PagingData<DevLifePost>> {
        return DevLifePageSource(
            api,
            sectionType
        ).createPager().flow
    }
}