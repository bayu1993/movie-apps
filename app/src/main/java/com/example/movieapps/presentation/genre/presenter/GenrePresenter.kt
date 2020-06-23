package com.example.movieapps.presentation.genre.presenter

import com.example.movieapps.BuildConfig
import com.example.movieapps.data.model.GenreResponse
import com.example.movieapps.network.repo.GenreRepo
import com.example.movieapps.network.repo.MovieRepoCallback
import com.example.movieapps.presentation.genre.view.GenreView

class GenrePresenter(private val view: GenreView, private val repo: GenreRepo) {
    fun getMovieGenres() {
        view.onHide()
        repo.getMovieGenres(BuildConfig.API_KEY, object : MovieRepoCallback<GenreResponse> {
            override fun onDataLoad(data: GenreResponse) {
                view.onSuccess(data)
            }

            override fun onError(error: Throwable) {
                view.onError(error)
            }

        })
        view.onShow()
    }
}