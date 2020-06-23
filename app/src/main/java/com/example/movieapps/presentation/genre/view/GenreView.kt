package com.example.movieapps.presentation.genre.view

import com.example.movieapps.data.model.GenreResponse

interface GenreView {
    fun onHide()
    fun onShow()
    fun onError(error: Throwable)
    fun onSuccess(data: GenreResponse)
}