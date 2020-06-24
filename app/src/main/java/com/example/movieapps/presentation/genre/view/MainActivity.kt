package com.example.movieapps.presentation.genre.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapps.BuildConfig
import com.example.movieapps.R
import com.example.movieapps.data.model.Genre
import com.example.movieapps.data.model.GenreResponse
import com.example.movieapps.network.repo.GenreRepo
import com.example.movieapps.presentation.genre.adapter.GenreAdapter
import com.example.movieapps.presentation.genre.presenter.GenrePresenter
import com.example.movieapps.presentation.genre.view.GenreView
import com.example.movieapps.utils.gone
import com.example.movieapps.utils.invisible
import com.example.movieapps.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GenreView {

    private lateinit var adapter: GenreAdapter
    private lateinit var presenter: GenrePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GenreAdapter {
            Snackbar.make(rv_genre, it.name, Snackbar.LENGTH_SHORT).show()
        }
        presenter = GenrePresenter(this, GenreRepo())

        rv_genre.layoutManager = GridLayoutManager(this, 2)
        rv_genre.adapter = adapter
        presenter.getMovieGenres()

    }

    override fun onHide() {
        progress_genre.visible()
        rv_genre.invisible()
    }

    override fun onShow() {
        rv_genre.visible()
        progress_genre.gone()
    }

    override fun onError(error: Throwable) {
        Snackbar.make(rv_genre, "Error : ${error.localizedMessage}", Snackbar.LENGTH_INDEFINITE).setAction("Retry") {
            presenter.getMovieGenres()
        }.show()
    }

    override fun onSuccess(data: GenreResponse) {
        adapter.addData(data.genres as MutableList<Genre>)
    }
}
