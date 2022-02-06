package ru.zdanovich.developerslife.presentation.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.LoadableResult
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.domain.repositoty.DevLifePostRepository
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val repository: DevLifePostRepository
): ViewModel() {
    lateinit var type: SectionType

    /** Получение списка постов */
    private val _postsLiveData = MutableLiveData<PagingData<DevLifePost>>()
    val postsLiveData: LiveData<PagingData<DevLifePost>> = _postsLiveData

    /** Состояния загрузки списка постов */
    private val _postsPagingStateLiveData = MutableLiveData<LoadableResult<Unit>>()
    val postPagingStateLiveData: LiveData<LoadableResult<Unit>> =
        _postsPagingStateLiveData

    fun launchPagingData() = viewModelScope.launch {
        repository.getPosts(sectionType = type)
            .cachedIn(viewModelScope)
            .collectLatest { _postsLiveData.postValue(it) }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _postsPagingStateLiveData.bindPagingState(loadState)
    }

    private fun MutableLiveData<LoadableResult<Unit>>.bindPagingState(
        loadState: CombinedLoadStates,
    ) {
        when (loadState.source.refresh) {
            is LoadState.NotLoading -> this.postValue(LoadableResult.success(Unit))
            is LoadState.Loading -> this.postValue(LoadableResult.loading())
            is LoadState.Error -> this.postValue(
                LoadableResult.failure(
                    (loadState.source.refresh as LoadState.Error).error,
                )
            )
        }
    }
}