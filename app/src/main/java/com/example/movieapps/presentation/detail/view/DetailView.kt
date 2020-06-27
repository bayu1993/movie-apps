package com.example.movieapps.presentation.detail.view

import com.example.movieapps.data.response.UserReviewResponse

interface DetailView{
    fun onHide()
    fun onShow()
    fun onError(error: Throwable)
    fun onSuccess(data: UserReviewResponse)
}