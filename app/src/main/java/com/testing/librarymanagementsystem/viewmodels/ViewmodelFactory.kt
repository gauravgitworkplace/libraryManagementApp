package com.testing.librarymanagementsystem.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testing.librarymanagementsystem.feature.searchscreeen.SearchBooksViewModel

class MyViewModelFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class SearchViewModelFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchBooksViewModel::class.java)) {
            return SearchBooksViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}