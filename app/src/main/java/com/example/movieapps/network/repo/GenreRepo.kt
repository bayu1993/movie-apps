package com.example.movieapps.network.repo

import com.example.movieapps.data.model.GenreResponse
import com.example.movieapps.network.ApiService
import com.example.movieapps.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GenreRepo {
    fun getMovieGenres(apiKey: String, callback: MovieRepoCallback<GenreResponse>) {
        RetrofitService.createService(ApiService::class.java)
            .getMovieGenres(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                callback.onDataLoad(data)
            }, { error ->
                callback.onError(error)
            })
    }
}