package com.neo.marvelcharacters.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.neo.marvelcharacters.data.remote.service.MarvelService
import com.neo.marvelcharacters.databinding.ActivityMainBinding
import com.neo.marvelcharacters.presentation.model.MarvelCharacterDiff
import com.neo.marvelcharacters.presentation.ui.adapter.CharactersAdapter
import com.neo.marvelcharacters.presentation.viewmodel.MainUiEffect
import com.neo.marvelcharacters.presentation.viewmodel.MainViewModel
import com.neo.marvelcharacters.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val charactersAdapter by lazy {
        CharactersAdapter(MarvelCharacterDiff())
    }

    @Inject
    lateinit var service: MarvelService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setupView()
        setupListeners()
        setupObservers()

        viewModel.requestFirstCharacters()
        viewModel.requestPaginatedCharacters()
    }

    private fun setupView() = with(binding) {
        rvCharacters.adapter = charactersAdapter
    }

    private fun setupListeners() {
        charactersAdapter.addLoadStateListener { loadState ->

            val error = loadState.refresh is LoadState.Error ||
                    loadState.append is LoadState.Error

            if (error) {
                showConnectionError()
            }

            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.isVisible = true
                binding.progressBar.playAnimation()
            } else {
                binding.progressBar.isVisible = false
                binding.progressBar.pauseAnimation()
            }
        }
    }

    private fun setupObservers() = lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                viewModel.uiState.collectLatest { state ->
                    charactersAdapter.submitData(state.paginatedCharacters)
                }
            }

            launch {
                viewModel.uiEffect.collect { effect ->
                    when (effect) {
                        MainUiEffect.Error -> showConnectionError()
                    }
                }
            }
        }
    }

    private fun showConnectionError() {
        binding.showSnackbar(
            message = "Sem conexão!"
        )
    }

}