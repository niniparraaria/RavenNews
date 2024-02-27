package com.raven.detail.presentacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.detail.domain.DetailDataSource
import com.raven.home.domain.models.NewsData
import com.raven.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val dataSource: DetailDataSource) : ViewModel() {
    private val _state = MutableStateFlow(NewsItemState())
    val state: StateFlow<NewsItemState> = _state

    fun getNewItem(id:Long) {
        viewModelScope.launch {
            dataSource.getNewItem(id).collect{ result ->
                when(result.status){
                    NetworkStatus.ERROR-> {
                        _state.value = _state.value.copy(status = result.status)
                    }
                    NetworkStatus.SUCCESS -> {
                        _state.value = _state.value.copy(data = result.data, status = result.status)
                    }
                    NetworkStatus.LOADING -> {
                        _state.value = _state.value.copy(status = result.status)
                    }
                }
            }
        }
    }
    data class NewsItemState(val data: NewsData.Result? = null, val status:NetworkStatus = NetworkStatus.LOADING)
}
