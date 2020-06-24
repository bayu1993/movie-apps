package com.example.movieapps.presentation.genre.view

interface MovieView <T>{
    fun onHide()
    fun onShow()
    fun onError(error: Throwable)
    fun onSuccess(data: T)
}