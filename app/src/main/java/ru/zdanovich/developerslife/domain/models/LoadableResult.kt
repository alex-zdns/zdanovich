package ru.zdanovich.developerslife.domain.models

sealed class LoadableResult<R> {

    class Loading<R> : LoadableResult<R>()
    class Success<R>(val value: R) : LoadableResult<R>()
    class Failure<R>(val throwable: Throwable
    ) : LoadableResult<R>()

    companion object {

        fun <R> loading(): LoadableResult<R> = Loading()
        fun <R> success(value: R): LoadableResult<R> = Success(value)

        fun <R> failure(
            throwable: Throwable
        ): LoadableResult<R> = Failure(throwable)
    }
}
