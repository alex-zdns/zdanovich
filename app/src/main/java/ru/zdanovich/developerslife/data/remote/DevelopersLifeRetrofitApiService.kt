package ru.zdanovich.developerslife.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.zdanovich.developerslife.data.remote.models.DevLifePostResponse
import ru.zdanovich.developerslife.data.remote.models.PageInfoResponse

interface DevelopersLifeRetrofitApiService {
    @GET("{section}/{page}?json=true")
    suspend fun getPosts(
        @Path("section") section: String,
        @Path("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): PageInfoResponse<DevLifePostResponse>
}