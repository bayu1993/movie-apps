package com.example.movieapps.data.response

import com.example.movieapps.data.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)