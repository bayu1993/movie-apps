package com.example.movieapps.network

import com.example.movieapps.data.model.GenreResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getMovieGenres(@Query("api_key") apiKey:String) : Observable<GenreResponse>
}