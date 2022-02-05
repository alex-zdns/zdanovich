package ru.zdanovich.developerslife.data.remote

import ru.zdanovich.developerslife.data.remote.converters.DevLifePostConvector
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import ru.zdanovich.developerslife.domain.models.DevLifePost
import javax.inject.Inject

class DevLifeApiServiceImpl @Inject constructor(
    private val api: DevelopersLifeRetrofitApiService,
    private val convector: DevLifePostConvector
    ): DevLifeApiService {
    override suspend fun getDevLifePosts(): List<DevLifePost> {
        return api.getPosts()
            .posts?.map { convector.convert(it) }?: emptyList()
    }
}