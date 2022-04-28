package com.neo.marvelcharacters.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.neo.marvelcharacters.data.remote.service.MarvelService
import com.neo.marvelcharacters.databinding.ActivityMainBinding
import com.neo.marvelcharacters.presentation.model.MarvelCharacterDiff
import com.neo.marvelcharacters.presentation.ui.adapter.CharactersAdapter
import com.neo.marvelcharacters.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

        viewModel.getAllCharacters()
    }

    private fun setupView() = with(binding) {
        rvCharacters.adapter = charactersAdapter
    }

    private fun setupListeners() = lifecycleScope.launch {
        viewModel.uiState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .collectLatest { state ->
                charactersAdapter.submitData(state.characters)
            }
    }
}