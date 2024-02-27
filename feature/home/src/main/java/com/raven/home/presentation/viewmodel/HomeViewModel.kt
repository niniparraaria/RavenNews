package com.raven.home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.models.NewsState
import com.raven.network.NetworkResult
import com.raven.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val dataSource: HomeDataSource) : ViewModel() {
    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state
    private val _tabState = mutableIntStateOf(0)
    val tabState: State<Int> = _tabState
    init {
       getNews()
    }
    fun setTabIndex(index:Int){
        _tabState.intValue = index
    }
    fun getNews() {
        val period = when(_tabState.intValue){
            0 -> "1"
            1 -> "7"
            else -> "30"
        }
        viewModelScope.launch {
            dataSource.getNews(period).collect{ result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        _state.value = state.value.copy(status = NetworkStatus.LOADING)
                    }
                    is NetworkResult.Success -> {
                        _state.value = state.value.copy(
                            data = result.data ?: emptyList(),
                            status = NetworkStatus.SUCCESS
                        )
                    }
                    is NetworkResult.Error -> {
                        _state.value = state.value.copy(errorMessage = result.errorMessage,
                            status = NetworkStatus.ERROR
                        )
                    }
                }
            }
        }
    }
}
