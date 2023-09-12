package com.example.books.network

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "AIzaSyBEH16r8IykbUOTOYNDw5vByVFvgnvt1BU"

interface ApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY
    ) : ApiResponse
}