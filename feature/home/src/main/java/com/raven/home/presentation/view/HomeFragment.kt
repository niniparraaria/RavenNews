package com.raven.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ninestudios.ui.ErrorScreen
import com.ninestudios.ui.LoadingScreen
import com.ninestudios.ui.RavenTopBar
import com.ninestudios.ui.ui.theme.white
import com.ninestudios.ui.views.RavenTabs
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.presentation.viewmodel.HomeViewModel
import com.raven.network.NetworkStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel:HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding =  HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeCompose.setContent {
            NewsListScreen()
        }
    }

    @Composable
    fun NewsListScreen() {
        val state = viewModel.state.collectAsState()
        var tabIndex = viewModel.tabState.value
        Surface {
            when(state.value.status){
                NetworkStatus.ERROR -> {
                    ErrorScreen(modifier = Modifier
                        .fillMaxSize()
                        .background(white), onTryAgain = {
                            viewModel.getNews()
                    })
                }
                NetworkStatus.SUCCESS -> {
                    val data = state.value.data ?: emptyList()
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(white)) {
                        RavenTopBar(title = "Most Popular")
                        val tabs = listOf("Last Day", "Last 7 Days", "Last 30 Days")
                        RavenTabs(tabs , onTab = {
                            viewModel.setTabIndex(it)
                            viewModel.getNews()
                        }, tabIndex )
                        LazyColumn(
                            Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 16.dp)
                        ) {
                            items(data.size) { i ->
                                NewsItem(data[i], onItemClick = {
                                    goToDetail(it)
                                })
                            }
                        }
                    }

                }
                else -> {
                    LoadingScreen(
                        Modifier
                            .fillMaxSize()
                            .background(white))
                }
            }
        }

    }

    private fun goToDetail(id:Long){
        val action = HomeFragmentDirections.actionToDetailFragment(id)
        findNavController().navigate(action)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
