package com.testing.librarymanagementsystem.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.testing.librarymanagementsystem.database.AdminLoginCredentials

class AdminLoginCredentialsConverter {

    @TypeConverter
    fun fromAdminLoginCredentials(value: AdminLoginCredentials?): String? {
        return value?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toAdminLoginCredentials(value: String?): AdminLoginCredentials? {
        return value?.let { Gson().fromJson(it, AdminLoginCredentials::class.java) }
    }
}
