package com.example.books.data

import com.example.books.network.ApiResponse
import com.example.books.network.ApiService
import com.example.books.network.Item

interface Repository {
    suspend fun getBooks(query: String): ApiResponse
}

class NetworkRepository(
    private val apiService: ApiService
) : Repository {
    override suspend fun getBooks(query: String): ApiResponse = apiService.getBooks(query)
}