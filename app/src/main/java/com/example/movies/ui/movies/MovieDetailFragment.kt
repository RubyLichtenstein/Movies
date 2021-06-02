package com.example.movies.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movies.data.Movie
import com.example.movies.databinding.MoviesFragmentBinding
import com.example.movies.viewmodel.MoviesViewModel
import com.example.movies.viewmodel.UiState
import kotlinx.coroutines.flow.collect

class MovieDetailFragment : Fragment() {
    private val viewModel: MoviesViewModel by activityViewModels()

    companion object {
        fun newInstance(movie: Movie): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}