package com.example.movieapps.data.response

import com.example.movieapps.data.model.UserReview
import com.google.gson.annotations.SerializedName

data class UserReviewResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<UserReview>
)
