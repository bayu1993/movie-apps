package com.example.movieapps.network.repo

import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.data.response.MovieResponse
import com.example.movieapps.data.response.UserReviewResponse
import com.example.movieapps.network.ApiService
import com.example.movieapps.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepo {
    fun getMoviesByGenre(apiKey: String, page: Int, id: String, callback: MovieRepoCallback<MovieResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getMoviesByGenre(apiKey = apiKey, page = page, genreIds = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })
    }

    fun getMovieDetail(apiKey: String, id: Int, callback: MovieRepoCallback<MovieDetailResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getMovieDetail(movieId = id, apiKey = apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })
    }

    fun getMovieReview(apiKey: String, id: Int, page: Int, callback: MovieRepoCallback<UserReviewResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getMovieReview(movieId = id, apiKey = apiKey, page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })
    }
}