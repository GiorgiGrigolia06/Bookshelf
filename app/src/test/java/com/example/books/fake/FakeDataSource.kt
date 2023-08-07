package com.example.books.fake

import com.example.books.network.ApiResponse
import com.example.books.network.ImageLinks
import com.example.books.network.Item
import com.example.books.network.VolumeInfo

object FakeDataSource {

    private const val idOne = "id1"
    private const val titleOne = "Title"
    private val authorsOne = listOf("Author")
    private const val publishedDateOne = "1997"
    private const val descriptionOne = "Description"
    private val imageLinksOne = ImageLinks("imageLink")

    private const val idTwo = "id 2"
    private const val titleTwo = "Title 2"
    private val authorsTwo = listOf("Author 2")
    private const val publishedDateTwo = "1997 2"
    private const val descriptionTwo = "Description 2"
    private val imageLinksTwo = ImageLinks("imageLink 2")


    private val itemList = listOf(
        Item(
            id = idOne,
            volumeInfo = VolumeInfo(
                title = titleOne,
                authors = authorsOne,
                publishedDate = publishedDateOne,
                description = descriptionOne,
                imageLinks = imageLinksOne
            )
        ),

        Item(
            id = idTwo,
            volumeInfo = VolumeInfo(
                title = titleTwo,
                authors = authorsTwo,
                publishedDate = publishedDateTwo,
                description = descriptionTwo,
                imageLinks = imageLinksTwo
            )
        )
    )

    val apiResponse = ApiResponse("Kind", 2, itemList)
}