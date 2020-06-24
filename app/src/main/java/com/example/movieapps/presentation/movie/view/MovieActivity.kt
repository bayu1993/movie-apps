package com.example.movieapps.presentation.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.R
import com.example.movieapps.data.model.Genre
import com.example.movieapps.data.model.Movie
import com.example.movieapps.data.response.MovieResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.presentation.genre.view.MovieView
import com.example.movieapps.presentation.movie.adapter.MovieAdapter
import com.example.movieapps.presentation.movie.presenter.MoviePresenter
import com.example.movieapps.utils.gone
import com.example.movieapps.utils.invisible
import com.example.movieapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity(), MovieView<MovieResponse> {

    private lateinit var presenter: MoviePresenter
    private lateinit var adapter: MovieAdapter
    private var id:Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(EXTRA_MOVIE)){
            val genre = intent.getParcelableExtra<Genre>(EXTRA_MOVIE)
            genre?.let {
                id = it.id
                supportActionBar?.title = it.name
            }
            presenter = MoviePresenter(MovieRepo(), this)
            presenter.getMoviesByGenre(1,id.toString())
        } else finish()

        adapter = MovieAdapter {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        }

        rv_movie.layoutManager = LinearLayoutManager(this)
        rv_movie.adapter = adapter
    }

    override fun onShow() {
        progress_movie.gone()
        rv_movie.visible()
    }

    override fun onHide() {
        progress_movie.visible()
        rv_movie.invisible()
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
        Log.d("movieapps", "error movieactivity >> ${error.localizedMessage}")
    }

    override fun onSuccess(data: MovieResponse) {
        Log.d("movieapps", "data movie movieactivity >> ${Gson().toJsonTree(data)}")
        adapter.setData(data.results as MutableList<Movie>)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object{
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}
