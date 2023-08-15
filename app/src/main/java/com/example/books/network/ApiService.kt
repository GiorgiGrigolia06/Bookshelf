package com.example.books.network

import com.example.books.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = BuildConfig.API_KEY

interface ApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY
    ) : ApiResponse
}