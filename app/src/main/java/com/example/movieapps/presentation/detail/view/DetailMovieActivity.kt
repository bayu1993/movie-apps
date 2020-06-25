package com.example.movieapps.presentation.detail.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapps.R
import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.presentation.detail.presenter.DetailMoviePresenter
import com.example.movieapps.presentation.genre.view.MovieView
import com.google.gson.Gson

class DetailMovieActivity : AppCompatActivity(), MovieView<MovieDetailResponse> {

    private var id:Int = 0
    private lateinit var presenter: DetailMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra(EXTRA_MOVIE_ID)){
            id = intent.getIntExtra(EXTRA_MOVIE_ID,0)
            presenter = DetailMoviePresenter()
            presenter.getMovieDetail(id, MovieRepo(),this)
        }
    }

    override fun onHide() {

    }

    override fun onShow() {

    }

    override fun onError(error: Throwable) {
        Log.d("movieapps","detail movie error: ${Gson().toJsonTree(error)}")
    }

    override fun onSuccess(data: MovieDetailResponse) {
        Log.d("movieapps","detail movie data: ${Gson().toJsonTree(data)}")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object{
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }
}
