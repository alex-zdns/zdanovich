package ru.zdanovich.developerslife.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper
import ru.zdanovich.developerslife.R
import ru.zdanovich.developerslife.domain.models.LoadableResult

class StateViewFlipper(context: Context, attrs: AttributeSet? = null) :
    ViewFlipper(context, attrs) {

    enum class State(val displayedChild: Int) {
        LOADING(0),
        ERROR(1),
        DATA(2),
        CUSTOM(3)
    }

    private var state = State.LOADING

    private var loadingView: View
    private lateinit var errorView: View

    private val buttonError by lazy { errorView.findViewById<TextView>(R.id.textViewRetry) }

    private var retryCallback: (() -> Unit)? = null

    init {
        val layoutInflater = LayoutInflater.from(context)

        loadingView = layoutInflater.inflate(R.layout.item_loading, this, false)
        addView(loadingView)

        errorView = layoutInflater.inflate(R.layout.item_error, this, false)
        addView(errorView)

        buttonError.setOnClickListener {
            retryCallback?.invoke()
        }
    }

    fun <T> setStateFromResult(loadableResult: LoadableResult<T>) {
        when (loadableResult) {
            is LoadableResult.Loading -> setStateLoading()
            is LoadableResult.Success -> setStateData()
            is LoadableResult.Failure -> setStateError()
        }
    }

    fun setRetryMethod(retry: () -> Unit) {
        retryCallback = retry
    }

    fun setCustomState() {
        changeState(State.CUSTOM)
    }

    private fun changeState(newState: State) {
        if (state != newState || displayedChild != newState.displayedChild) {
            state = newState
            displayedChild = newState.displayedChild
        }
    }

    private fun setStateLoading() {
        changeState(State.LOADING)
    }

    private fun setStateError() {
        changeState(State.ERROR)
    }

    private fun setStateData() {
        changeState(State.DATA)
    }
}
