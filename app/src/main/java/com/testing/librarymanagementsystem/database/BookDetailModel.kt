package com.testing.librarymanagementsystem.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookDetailModel(
    @PrimaryKey(autoGenerate = true) val uid: Int =0,
    @ColumnInfo(name = "auth_number") val authNumber: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "edition") val edition: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isbn") val isbn: String
)
