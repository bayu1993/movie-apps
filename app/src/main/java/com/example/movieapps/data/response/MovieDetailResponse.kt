package com.example.movieapps.data.response

import com.example.movieapps.data.model.Genre
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("budget")
    val budget: Int?,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val production_countries: List<ProductionCountry>,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("revenue")
    val revenue: Int?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spoken_languages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int?,
    @SerializedName("videos")
    val videos: ResultVideos
)

data class ResultVideos(
    @SerializedName("results")
    val results:List<Videos>
)

data class Videos(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("size")
    val size: Int?
)


data class ProductionCompany(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logo_path: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val origin_country: String?
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("name")
    val name: String?
)

data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("name")
    val name: String?
)