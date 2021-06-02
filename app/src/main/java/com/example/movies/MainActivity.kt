package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.movies.data.Movie
import com.example.movies.ui.MovieDetailFragment
import com.example.movies.ui.MoviesFragment
import com.example.movies.viewmodel.MoviesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull

class MainActivity : AppCompatActivity() {
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MoviesFragment.newInstance())
                .commitNow()
        }

        lifecycleScope.launchWhenCreated {
            viewModel
                .navigateToMovieDetail
                .filterNotNull()
                .collect(::navigateToMovieDetail)
        }
    }

    private fun navigateToMovieDetail(movie: Movie) {
        title = "${movie.title} - ${movie.year}"
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.container, MovieDetailFragment.newInstance())
            addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        title = "Movies"
    }
}