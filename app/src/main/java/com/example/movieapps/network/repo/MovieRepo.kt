package com.example.movieapps.network.repo

import com.example.movieapps.data.response.MovieResponse
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
}