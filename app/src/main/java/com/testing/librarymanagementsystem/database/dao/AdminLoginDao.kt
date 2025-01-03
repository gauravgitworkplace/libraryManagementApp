package com.testing.librarymanagementsystem.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminLoginDao {

    @Query("Select * From adminlogincredentialsmodel where admin_username like :valueUsername and admin_user_password like :valuePassword" )
    fun getAdminLoginDetails(valueUsername:String, valuePassword:String): Flow<List<AdminLoginCredentialsModel>>

    @Insert
    suspend fun insertAdminLoginDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel)

    @Update
    suspend fun updateAdminLoginDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel)

    @Delete
    suspend fun deleteAdminLoginDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel)
}