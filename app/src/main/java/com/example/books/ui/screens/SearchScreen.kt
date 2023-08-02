package com.example.books.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.books.R

@Composable
fun SearchScreen(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchScreenDescription(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.home_screen_description_padding),
                    end = dimensionResource(R.dimen.home_screen_description_padding),
                    bottom = dimensionResource(R.dimen.home_screen_description_padding)
                )
            )

            SearchBar(
                placeholder = R.string.search_input_placeholder,
                value = value,
                onValueChange = onValueChange,
                onSearch = onSearch
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @StringRes placeholder: Int,
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(stringResource(placeholder)) },
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            trailingIcon =
            {
                Icon(
                    painterResource(R.drawable.baseline_search_24),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions { onSearch() },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = colorResource(R.color.search_bar_border),
                containerColor = if (isSystemInDarkTheme()) colorResource(R.color.search_bar_container_color_dark_mode) else Color.Transparent
            ),
            modifier = Modifier
                .widthIn(max = dimensionResource(R.dimen.search_bar_max_width))
            )
        }
}


@Composable
fun SearchScreenDescription(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.home_screen_description),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}