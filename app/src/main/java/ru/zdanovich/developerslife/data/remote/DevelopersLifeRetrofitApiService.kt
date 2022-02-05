package ru.zdanovich.developerslife.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.zdanovich.developerslife.data.remote.models.DevLifePostsResponse

interface DevelopersLifeRetrofitApiService {
    @GET("{section}/0?json=true")
    suspend fun getPosts(
        @Path("section") section: String = "top",
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 10
    ): DevLifePostsResponse
}