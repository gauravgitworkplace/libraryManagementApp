package com.testing.librarymanagementsystem.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.testing.librarymanagementsystem.database.BookDetailModel
import com.testing.librarymanagementsystem.database.LibraryDatabase
import com.testing.librarymanagementsystem.repository.BookRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookViewModel(application: Context) : AndroidViewModel(application as Application) {
    private val appContext = application.applicationContext
    private val db = LibraryDatabase.getDB(appContext)
    private val bookDao = db.bookDao()
    private val repository = BookRepository(bookDao)


    private val _bookLists = MutableStateFlow(emptyList<BookDetailModel>())
    val bookLists = _bookLists.asStateFlow()


    var uidState by mutableStateOf(0)
        private set
    var authNameState by mutableStateOf("")
        private set
    var titleState by mutableStateOf("")
        private set
    var priceeState by mutableStateOf("")
        private set
    var categoryState by mutableStateOf("")
        private set
    var editionState by mutableStateOf("")
        private set
    var isbnState by mutableStateOf("")

    fun updateAuthText(newText: String): String {
        authNameState = newText
        return authNameState

    }

    fun updateTitleText(newText: String): String {
        titleState = newText
        return titleState
    }

    fun updatePriceText(newText: String): String {
        priceeState = newText
        return priceeState
    }

    fun updateCategoryText(newText: String): String {
        categoryState = newText
        return categoryState
    }

    fun updateEditionText(newText: String): String {
        editionState = newText
        return editionState
    }

    fun updateIsbnText(newText: String): String {
        isbnState = newText
        return isbnState
    }


    fun getResponseModel(response: BookDetailModel) {
        uidState = response.uid
        authNameState = response.authNumber
        titleState = response.title
        priceeState = response.price
        categoryState = response.category
        editionState = response.edition
        isbnState = response.isbn
        Log.d("TAG", "getResponseModel: $uidState  $response")
    }


    fun saveBookDetails(bookDetailModel: BookDetailModel) {
        viewModelScope.launch {
            Log.d("TAG", "saveBookDetails: $uidState  $bookDetailModel")
            if (uidState == 0) {
                repository.insertBookDetails(bookDetailModel)
            } else {
                repository.updateBookInfo(bookDetailModel.copy())

            }
        }
    }



    fun deleteBookList(bookDetailModel: BookDetailModel) {
        viewModelScope.launch {
            repository.deleteBookDetailsInfo(bookDetailModel)
        }
    }


    private fun getAllListDetails() {
        viewModelScope.launch(IO) {
            repository.list.collectLatest {

                val list = _bookLists.tryEmit(it)
                val datalis = bookLists.value
                Log.d("TAG", "getAllListDetails: $list  $datalis")
            }
        }
    }


    private val getAllBooks: kotlinx.coroutines.flow.Flow<List<BookDetailModel>> = repository.list


    init {
        getAllListDetails()
        viewModelScope.launch {
            getAllBooks.collectLatest { list ->
                Log.d("TAG", "list: $list")
            }
        }
    }

    fun getValidatedNumber(text: String): String {
        // Start by filtering out unwanted characters like commas and multiple decimals
        val filteredChars = text.filterIndexed { index, c ->
            c in "0123456789" ||                      // Take all digits
                    (c == '.' && text.indexOf('.') == index)  // Take only the first decimal
        }
        // Now we need to remove extra digits from the input
        return if(filteredChars.contains('.')) {
            val beforeDecimal = filteredChars.substringBefore('.')
            val afterDecimal = filteredChars.substringAfter('.')
            beforeDecimal.take(5) + "." + afterDecimal.take(2)    // If decimal is present, take first 3 digits before decimal and first 2 digits after decimal
        } else {
            filteredChars.take(5)                     // If there is no decimal, just take the first 3 digits
        }
    }

}