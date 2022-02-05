package ru.zdanovich.developerslife.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.zdanovich.developerslife.presentation.recyclerview.DiffUtilCallbackFactory
import ru.zdanovich.developerslife.presentation.recyclerview.DiffUtilItemCallbackFactory

@Module
@InstallIn(SingletonComponent::class)
open class ApplicationModule {

    @Provides
    fun provideDiffUtilItemCallbackFactory(): DiffUtilItemCallbackFactory {
        return DiffUtilItemCallbackFactory()
    }

    @Provides
    fun provideDiffUtilCallbackFactory(
        diffUtilItemCallbackFactory: DiffUtilItemCallbackFactory
    ): DiffUtilCallbackFactory {
        return DiffUtilCallbackFactory(diffUtilItemCallbackFactory)
    }
}
