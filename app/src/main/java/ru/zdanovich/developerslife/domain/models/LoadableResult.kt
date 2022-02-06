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

    val isLoading: Boolean get() = this is Loading

    val isSuccess: Boolean get() = this is Success

    val isFailure: Boolean get() = this is Failure

    inline fun doOnComplete(tag: String, operation: (LoadableResult<R>) -> Unit) {
        when (this) {
            is Loading -> { /* Skip it */
            }
            is Failure -> operation(failure(throwable))
            is Success -> operation(success(value))
        }
    }

    inline fun doOnLoading(operation: () -> Unit) {
        when (this) {
            is Loading -> operation()
            is Failure -> { /* Skip it */
            }
            is Success -> { /* Skip it */
            }
        }
    }

    inline fun doOnSuccess(operation: (R) -> Unit) {
        when (this) {
            is Loading -> { /* Skip it */
            }
            is Failure -> { /* Skip it */
            }
            is Success -> operation(value)
        }
    }

    inline fun doOnFailure(operation: (Throwable) -> Unit) {
        when (this) {
            is Loading -> { /* Skip it */
            }
            is Failure -> operation(throwable)
            is Success -> { /* Skip it */
            }
        }
    }
}
