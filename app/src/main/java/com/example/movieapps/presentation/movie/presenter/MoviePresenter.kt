package com.example.movieapps.presentation.movie.presenter

import com.example.movieapps.BuildConfig
import com.example.movieapps.data.response.MovieResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.network.repo.MovieRepoCallback
import com.example.movieapps.presentation.genre.view.MovieView

class MoviePresenter(private val repo: MovieRepo, private val callback: MovieView<MovieResponse>) {
    fun getMoviesByGenre(page: Int, id: String) {
        callback.onHide()
        repo.getMoviesByGenre(BuildConfig.API_KEY, page, id, object : MovieRepoCallback<MovieResponse> {
            override fun onDataLoad(data: MovieResponse) {
                callback.onShow()
                callback.onSuccess(data)
            }

            override fun onError(error: Throwable) {
                callback.onShow()
                callback.onError(error)
            }

        })
    }
}