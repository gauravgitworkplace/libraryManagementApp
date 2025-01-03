package com.testing.librarymanagementsystem.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testing.librarymanagementsystem.feature.adminsignup.AdminSignUpViewModel
import com.testing.librarymanagementsystem.feature.searchscreeen.SearchBooksViewModel

class CommonViewModelFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AdminSignUpViewModel::class.java -> AdminSignUpViewModel(application) as T
            SearchBooksViewModel::class.java -> SearchBooksViewModel(application) as T
            BookViewModel::class.java -> BookViewModel(application) as T
            else -> throw Throwable("Unsupported view model")
        }
    }
}
