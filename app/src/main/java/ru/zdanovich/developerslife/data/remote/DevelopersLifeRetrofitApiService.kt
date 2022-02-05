package ru.zdanovich.developerslife.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.zdanovich.developerslife.data.remote.models.DevLifePostsResponse

interface DevelopersLifeRetrofitApiService {
    @GET("{section}/{page}?json=true&pageSize={pageSize}")
    suspend fun getPosts(
        @Path("section") section: String,
        @Path("page") page: Int,
        @Path("pageSize") pageSize: Int
    ): DevLifePostsResponse
}