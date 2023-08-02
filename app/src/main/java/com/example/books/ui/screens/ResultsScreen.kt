package com.example.books.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.books.R
import com.example.books.network.ImageLinks
import com.example.books.network.Item

@Composable
fun ResultsScreen(
    booksUiState: BooksUiState,
) {
    when (booksUiState) {
        is BooksUiState.Success -> BooksLazyColumn(items = booksUiState.item.items)
        is BooksUiState.Error -> ErrorScreen()
        is BooksUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), Alignment.Center){
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = null
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), Alignment.Center){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = "Loading Failed", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun BookCard(
    image: ImageLinks,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(image.thumbnail.replace("http", "https")) // 🪳🧨
            .crossfade(true)
            .build(),
        contentDescription = null,
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun BooksLazyColumn(
    items: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = items, key = { item -> item.id }) {
            /**
             * The bug here almost bit harder than that chinese dude got bit by that bat in 2020 😀
             * The problem was that I was forgetting to update the thumbnail URL (http to https) 🙃
             */
            Log.d("Image URL", "Thumbnail URL: ${it.volumeInfo.imageLinks}")
            BookCard(
                image = it.volumeInfo.imageLinks,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}