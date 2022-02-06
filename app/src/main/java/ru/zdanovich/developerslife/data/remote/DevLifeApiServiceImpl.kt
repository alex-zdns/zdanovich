package ru.zdanovich.developerslife.data.remote

import ru.zdanovich.developerslife.data.remote.converters.DevLifePostConvector
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.PageInfo
import ru.zdanovich.developerslife.domain.models.SectionType
import javax.inject.Inject

class DevLifeApiServiceImpl @Inject constructor(
    private val api: DevelopersLifeRetrofitApiService,
    private val convector: DevLifePostConvector
) : DevLifeApiService {
    companion object {
        private const val LATEST_PATH = "latest"
        private const val HOT_PATH = "hot"
        private const val TOP_PATH = "top"
    }

    override suspend fun getDevLifePosts(sectionType: SectionType, page: Int, pageSize: Int): PageInfo<DevLifePost> {
        val pageInfoResponse = api.getPosts(section = getPathSection(sectionType), page = page, pageSize = pageSize)
        val items = pageInfoResponse
            .items?.map { convector.convert(it) } ?: emptyList()

        return PageInfo(
            items,
            pageInfoResponse.totalCount ?: 0
        )
    }

    private fun getPathSection(type: SectionType) = when (type) {
        SectionType.LATEST -> LATEST_PATH
        SectionType.HOT -> HOT_PATH
        SectionType.TOP -> TOP_PATH
    }
}