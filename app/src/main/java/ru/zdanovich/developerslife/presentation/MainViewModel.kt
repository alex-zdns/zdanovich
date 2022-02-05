package ru.zdanovich.developerslife.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.zdanovich.developerslife.domain.api.DevLifeApiService
import ru.zdanovich.developerslife.domain.models.DevLifePost
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: DevLifeApiService
): ViewModel() {
    private val _postsLiveData = MutableLiveData<List<DevLifePost>>()
    val postsLiveData: LiveData<List<DevLifePost>> = _postsLiveData


    fun loadPosts() {
        viewModelScope.launch {
            _postsLiveData.postValue(apiService.getDevLifePosts())
        }
    }
}