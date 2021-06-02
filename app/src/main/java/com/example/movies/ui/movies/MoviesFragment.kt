package com.example.movies.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movies.databinding.MoviesFragmentBinding
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.UiState
import kotlinx.coroutines.flow.collect

class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by activityViewModels()

    companion object {
        fun newInstance() = MoviesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesFragmentBinding.inflate(inflater, container, false)
        val adapter = MoviesAdapter {
            lifecycleScope.launchWhenStarted {
                viewModel.navigateToMovieDetail.emit(it)
            }
        }
        binding.moviesList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(
        adapter: MoviesAdapter,
        binding: MoviesFragmentBinding
    ) {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Success -> {
                        binding.loading.visibility = View.INVISIBLE
                        adapter.submitList(uiState.movies)
                    }
                    is UiState.Error -> {
                        binding.loading.visibility = View.INVISIBLE
                        // todo add error ui
                    }
                    UiState.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}