package ru.zdanovich.developerslife.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.zdanovich.developerslife.data.remote.DevLifeApiServiceImpl
import ru.zdanovich.developerslife.data.remote.DevelopersLifeRetrofitApiService
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ApiServiceModule {
    companion object {
        const val JSON_MIME_TYPE = "application/json"
        const val CONNECTION_TIMEOUTS_MS = 60_000L
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
            readTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)
            writeTimeout(CONNECTION_TIMEOUTS_MS, TimeUnit.MILLISECONDS)

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()
    }


    @ExperimentalSerializationApi
    @Provides
    @Singleton
    open fun provideRetrofit(
        json: Json,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            // TODO поправить
            .baseUrl("https://developerslife.ru/")
            .addConverterFactory(json.asConverterFactory(JSON_MIME_TYPE.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiService(retrofit: Retrofit): DevelopersLifeRetrofitApiService =
        retrofit.create(DevelopersLifeRetrofitApiService::class.java)

    // TODO (поправить)
    @Provides
    @Singleton
    fun bindDevLifeApiService(
        devLifeApiServiceImpl: DevLifeApiServiceImpl
    ): DevLifeApiService = devLifeApiServiceImpl
}