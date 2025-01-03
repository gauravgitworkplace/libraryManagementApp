package com.testing.librarymanagementsystem.repository

import com.testing.librarymanagementsystem.database.BookDetailModel
import com.testing.librarymanagementsystem.database.dao.LibraryDao
import kotlinx.coroutines.flow.Flow

class BookRepository(private val libraryDao: LibraryDao) {

    val list: Flow<List<BookDetailModel>> = libraryDao.getAllBookDetails()


    fun getSearchString(value: String): Flow<List<BookDetailModel>> {
        val searchedList: Flow<List<BookDetailModel>> =
            libraryDao.getSearchedBookList(value)
        return searchedList
    }

    suspend fun insertBookDetails(bookDetailModel: BookDetailModel) {
        libraryDao.insertBookDetails(bookDetailModel)
    }

    suspend fun deleteBookDetailsInfo(bookDetailModel: BookDetailModel) {
        libraryDao.deleteBookDetails(bookDetailModel)
    }

    suspend fun updateBookInfo(bookDetailModel: BookDetailModel) {
        libraryDao.updateBookDetails(bookDetailModel)
    }
}