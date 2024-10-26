package com.testing.librarymanagementsystem.feature.searchscreeen

import android.app.Application
import android.content.Context
import android.util.Log
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

class SearchBooksViewModel(application: Context) : AndroidViewModel(application as Application) {
    private val appContext = application.applicationContext
    private val db = LibraryDatabase.getDB(appContext)
    private val bookDao = db.bookDao()
    private val repository = BookRepository(bookDao)


    private val _searchBookLists = MutableStateFlow(emptyList<BookDetailModel>())
    val searchBookLists = _searchBookLists.asStateFlow()


     fun getAllListDetails(value:String) {
        viewModelScope.launch(IO) {
            repository.getSearchString("%$value%").collectLatest {

                val list = _searchBookLists.tryEmit(it)
                val datalis = searchBookLists.value
                Log.d("TAG", "getAllListDetails: $list  $datalis")
            }
        }
    }



    fun getValueForSearched(value: String) {
        repository.getSearchString(value)
    }


}