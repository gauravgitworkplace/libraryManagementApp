package com.testing.librarymanagementsystem

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.testing.librarymanagementsystem.database.LibraryDatabase

class LibraryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}