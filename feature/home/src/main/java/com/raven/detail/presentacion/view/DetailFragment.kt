package com.raven.detail.presentacion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ninestudios.ui.ErrorScreen
import com.ninestudios.ui.LoadingScreen
import com.ninestudios.ui.ui.theme.white
import com.raven.detail.presentacion.viewmodel.DetailViewModel
import com.raven.home.databinding.DetailFragmentBinding
import com.raven.network.NetworkStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel:DetailViewModel by viewModels()
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val args:DetailFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding =  DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailCompose.setContent {
            NewsListScreen()
        }
        val id = args.id
        viewModel.getNewItem(id)
    }

    @Composable
    fun NewsListScreen() {
        val state = viewModel.state.collectAsState()
        Surface {
            when(state.value.status){
                NetworkStatus.LOADING ->  {
                    LoadingScreen(
                        Modifier
                            .fillMaxSize()
                            .background(white))

                }
                NetworkStatus.ERROR ->  {
                    ErrorScreen(
                        Modifier
                            .fillMaxSize()
                            .background(white), onTryAgain = {
                                viewModel.getNewItem(args.id)
                        })

                }
                else -> {
                    val data = state.value.data!!
                    DetailItem(data = data, onBack = {
                        findNavController().popBackStack()
                    })
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
