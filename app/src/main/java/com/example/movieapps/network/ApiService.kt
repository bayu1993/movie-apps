package com.example.movieapps.network

import com.example.movieapps.data.response.GenreResponse
import com.example.movieapps.data.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey:String) : Observable<GenreResponse>

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("api_key") apiKey: String,
                         @Query("language") lang:String = "en-US",
                         @Query("sort_by") sortBy:String = "popularity.desc",
                         @Query("page") page:Int?,
                         @Query("with_genres") genreIds:String): Observable<MovieResponse>
}