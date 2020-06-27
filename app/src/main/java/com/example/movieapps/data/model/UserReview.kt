package com.example.movieapps.data.model

import com.google.gson.annotations.SerializedName


data class UserReview(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)