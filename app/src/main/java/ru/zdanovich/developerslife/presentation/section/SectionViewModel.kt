package ru.zdanovich.developerslife.presentation.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zdanovich.developerslife.domain.models.DevLifePost
import ru.zdanovich.developerslife.domain.models.SectionType
import ru.zdanovich.developerslife.domain.repositoty.DevLifePostRepository
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val repository: DevLifePostRepository
): ViewModel() {
    lateinit var type: SectionType

    private val _postsLiveData = MutableLiveData<PagingData<DevLifePost>>()
    val postsLiveData: LiveData<PagingData<DevLifePost>> = _postsLiveData

    fun launchPagingData() = viewModelScope.launch {
        repository.getPosts(sectionType = type)
            .cachedIn(viewModelScope)
            .collectLatest { _postsLiveData.postValue(it) }
    }
}