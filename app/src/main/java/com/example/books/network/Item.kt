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
    val title: String = "Title not available",
    val authors: List<String> = listOf("Authors not available"),
    val publishedDate: String = "Date not available",
    val description: String = "Description not available",
    val imageLinks: ImageLinks = ImageLinks(thumbnail = "Image not available")
)


@Serializable
data class ImageLinks(
    val thumbnail: String
)