package com.testing.librarymanagementsystem.repository

import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
import com.testing.librarymanagementsystem.database.dao.AdminLoginDao
import kotlinx.coroutines.flow.Flow

class AdminLoginRepository(private val adminLoginDao: AdminLoginDao) {

    fun loginList(valueUserName:String?,valuePassword:String? ): Flow<List<AdminLoginCredentialsModel>> = adminLoginDao.getAdminLoginDetails(valueUserName?:"", valuePassword?:"")


    suspend fun addAdminDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel) {
        adminLoginDao.insertAdminLoginDetails(adminLoginCredentialsModel)

    }

    suspend fun updateAdminDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel) {
        adminLoginDao.updateAdminLoginDetails(adminLoginCredentialsModel)
    }

}