package com.example.books.fake

import com.example.books.data.Repository
import com.example.books.network.ApiResponse

class FakeNetworkRepository: Repository {
    override suspend fun getBooks(query: String): ApiResponse {
        return FakeDataSource.apiResponse
    }
}
