package com.example.books.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.books.R
import com.example.books.network.ImageLinks
import com.example.books.network.Item
import com.example.books.network.VolumeInfo

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
            modifier = Modifier.size(dimensionResource(R.dimen.loading_screen_image_size)),
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
            Text(text = "Loading Failed", modifier = Modifier.padding(dimensionResource(R.dimen.loading_failed_padding)))
        }
    }
}

@Composable
fun BookCard(
    image: ImageLinks,
    itemInfo: VolumeInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraSmall,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_default_elevation)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ){
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.card_content_padding))) {
            Box{
                Column{
                    Text(
                        text = itemInfo.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.card_title_spacer)))
                }

                Divider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    thickness = dimensionResource(R.dimen.divider_thickness),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_between_title_and_content)))


            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(image.thumbnail.replace("http", "https")) // 🪳🧨
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.card_spacer_width)))

                Column {
                    Text(
                        text = if(itemInfo.authors.size > 1) "Authors: ${itemInfo.authors.joinToString(", ")}"
                        else "Author: ${itemInfo.authors.joinToString()}"
                    )

                    Text(
                        text = "Publish date: ${itemInfo.publishedDate.take(integerResource(R.integer.publish_date_max_shown_chars))}"
                    )

                    Box {
                        Text(
                            text = if(itemInfo.description === "Description not available") "Description not available"
                            else "Description:\n${itemInfo.description.take(integerResource(R.integer.description_max_shown_chars))}",
                            maxLines = integerResource(R.integer.description_max_lines),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BooksLazyColumn(
    items: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(R.dimen.lazy_column_horizontal_content_padding),
            vertical = dimensionResource(R.dimen.lazy_column_vertical_content_padding)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.lazy_column_vertical_arrangement)),
    ) {
        items(items = items, key = { item -> item.id }) {
            /**
             * The bug here almost bit harder than that Chinese dude got bit by that bat in 2020 😀
             * The problem was that I was forgetting to update the thumbnail URL (http to https) 🙃
             */
            Log.d("Image URL", "Thumbnail URL: ${it.volumeInfo.imageLinks}")
            BookCard(
                image = it.volumeInfo.imageLinks,
                itemInfo = it.volumeInfo
            )
        }
    }
}