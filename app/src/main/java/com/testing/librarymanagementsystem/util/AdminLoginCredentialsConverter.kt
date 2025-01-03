package com.testing.librarymanagementsystem.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel

class AdminLoginCredentialsConverter {

    @TypeConverter
    fun fromAdminLoginCredentials(value: AdminLoginCredentialsModel?): String? {
        return value?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toAdminLoginCredentials(value: String?): AdminLoginCredentialsModel? {
        return value?.let { Gson().fromJson(it, AdminLoginCredentialsModel::class.java) }
    }
}
