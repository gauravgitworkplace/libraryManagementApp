package com.testing.librarymanagementsystem.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.testing.librarymanagementsystem.database.BookDetailModel
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {

    @Query("Select * From bookdetailmodel")
    fun getAllBookDetails(): Flow<List<BookDetailModel>>


    @Query("Select * from bookdetailmodel where auth_number like :value or title like :value or category like :value")
    fun getSearchedBookList(value: String): Flow<List<BookDetailModel>>

    @Insert
    suspend fun insertBookDetails(bookDetailModel: BookDetailModel)

    @Update
    suspend fun updateBookDetails(bookDetailModel: BookDetailModel)

    @Delete
    suspend fun deleteBookDetails(bookDetailModel: BookDetailModel)


}