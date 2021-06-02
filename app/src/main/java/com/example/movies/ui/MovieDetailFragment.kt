package com.example.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movies.data.Movie
import com.example.movies.databinding.MovieDetailFragmentBinding
import com.example.movies.databinding.MoviesFragmentBinding
import com.example.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull

class MovieDetailFragment : Fragment() {
    private val viewModel: MoviesViewModel by activityViewModels()

    companion object {
        fun newInstance(): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenResumed {
            viewModel
                .navigateToMovieDetail
                .filterNotNull()
                .collect { movie ->
                    binding.moviePlot.text = movie.plot
                    Glide.with(binding.movieImage).load(movie.posterUrl).into(binding.movieImage)
                }
        }
        return binding.root
    }
}