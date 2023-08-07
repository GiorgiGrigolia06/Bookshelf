package com.example.books.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.books.BooksApplication
import com.example.books.data.Repository
import com.example.books.network.ApiResponse
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val item: ApiResponse) : BooksUiState
    object Loading: BooksUiState
    object Error: BooksUiState
}

class BooksViewModel(private val booksRepository: Repository): ViewModel() {
    var userInput: String by mutableStateOf("")
        private set

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    fun updateUserInput(input: String) {
        userInput = input
    }

    fun clearUserInput() {
        userInput = ""
    }

    fun getBooks() {
        booksUiState = BooksUiState.Loading
        viewModelScope.launch {
            booksUiState = try {
                BooksUiState.Success(
                    booksRepository.getBooks(userInput)
                )
            } catch (e: IOException) {
                BooksUiState.Error
            } catch (e: HttpException) {
                BooksUiState.Error
            } catch (e: SerializationException) {
                BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.repository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}