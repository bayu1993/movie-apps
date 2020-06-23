package com.example.movieapps.network.repo

interface MovieRepoCallback<T>{
    fun onDataLoad(data: T)
    fun onError(error: Throwable)
}