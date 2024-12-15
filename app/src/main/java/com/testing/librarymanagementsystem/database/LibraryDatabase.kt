package com.testing.librarymanagementsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testing.librarymanagementsystem.util.AdminLoginCredentialsConverter

@Database(entities = [BookDetailModel::class], version = 2)
@TypeConverters(AdminLoginCredentialsConverter::class)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun bookDao(): LibraryDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getDB(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        LibraryDatabase::class.java,
                        "library_db"
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}