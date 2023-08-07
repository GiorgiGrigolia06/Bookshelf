package com.example.books.fake

import com.example.books.network.ApiResponse
import com.example.books.network.ApiService

class FakeApiService: ApiService {
    override suspend fun getBooks(query: String, apiKey: String): ApiResponse {
        return FakeDataSource.apiResponse
    }
}