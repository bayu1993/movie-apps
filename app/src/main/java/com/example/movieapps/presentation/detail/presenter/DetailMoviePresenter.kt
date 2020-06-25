package com.example.movieapps.presentation.detail.presenter

import com.example.movieapps.BuildConfig
import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.network.repo.MovieRepoCallback
import com.example.movieapps.presentation.genre.view.MovieView

class DetailMoviePresenter {
    fun getMovieDetail(id:Int, repo:MovieRepo, callback:MovieView<MovieDetailResponse>){
        callback.onHide()
        repo.getMovieDetail(BuildConfig.API_KEY, id, object : MovieRepoCallback<MovieDetailResponse>{
            override fun onDataLoad(data: MovieDetailResponse) {
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