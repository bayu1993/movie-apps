package com.example.movieapps.network

import com.example.movieapps.data.response.GenreResponse
import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.data.response.MovieResponse
import com.example.movieapps.data.response.UserReviewResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey: String): Observable<GenreResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "en-US",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int?,
        @Query("with_genres") genreIds: String
    ): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "en-US",
        @Query("append_to_response") appendToResponse: String = "videos"
    ): Observable<MovieDetailResponse>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int?
    ): Observable<UserReviewResponse>
}