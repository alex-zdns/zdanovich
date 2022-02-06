package ru.zdanovich.developerslife.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.SectionType
import javax.inject.Inject

class DevLifePageSource @Inject constructor(
    private val api: DevLifeApiService,
    private val type: SectionType
) : PagingSource<Int, DevLifePost>() {

    companion object {
        const val DEFAULT_KEY = 0
        const val DEFAULT_LIMIT = 30
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DevLifePost> {
        val page = params.key ?: DEFAULT_KEY
        val limit = params.loadSize

        return try {
            val result = api.getDevLifePosts(sectionType = type, page = page, pageSize = limit)
            val nextOffset = page * DEFAULT_LIMIT + result.items.size
                    LoadResult.Page(
                data = result.items,
                prevKey = calcPrevKey(page),
                itemsBefore = calcItemsBefore(page),
                nextKey = calcNextKey(page, result.totalCount),
                itemsAfter = result.totalCount - nextOffset
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }

    private fun calcPrevKey(page: Int): Int? {
        if (page <= 0) return null
        return page - 1
    }

    private fun calcNextKey(page: Int, totalCount: Int): Int? {
        if (page * DEFAULT_LIMIT >= totalCount) return null
        return page + 1
    }

    private fun calcItemsBefore(page: Int): Int {
        if (page <= 0) return 0
        return page * DEFAULT_LIMIT
    }

    override fun getRefreshKey(state: PagingState<Int, DevLifePost>): Int? = state.anchorPosition
}