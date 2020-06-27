package com.example.movieapps.presentation.detail.presenter

import com.example.movieapps.BuildConfig
import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.data.response.UserReviewResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.network.repo.MovieRepoCallback
import com.example.movieapps.presentation.detail.view.DetailView
import com.example.movieapps.presentation.genre.view.MovieView

class DetailMoviePresenter {
    fun getMovieDetail(id: Int, repo: MovieRepo, callback: MovieView<MovieDetailResponse>) {
        callback.onHide()
        repo.getMovieDetail(BuildConfig.API_KEY, id, object : MovieRepoCallback<MovieDetailResponse> {
            override fun onDataLoad(data: MovieDetailResponse) {
                callback.onShow()
                callback.onSuccess(data)
            }

            override fun onError(error: Throwable) {
                callback.onHide()
                callback.onError(error)
            }
        })
    }

    fun getMovieReview(id: Int, page: Int, repo: MovieRepo, callback: DetailView) {
        callback.onHide()
        repo.getMovieReview(BuildConfig.API_KEY, id, page, object : MovieRepoCallback<UserReviewResponse> {
            override fun onDataLoad(data: UserReviewResponse) {
                callback.onShow()
                callback.onSuccess(data)
            }

            override fun onError(error: Throwable) {
                callback.onHide()
                callback.onError(error)
            }
        })
    }
}