package com.example.books.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Item>
)

@Serializable
data class Item(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)