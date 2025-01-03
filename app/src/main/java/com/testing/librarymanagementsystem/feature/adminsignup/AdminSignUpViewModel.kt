package com.testing.librarymanagementsystem.feature.adminsignup

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
import com.testing.librarymanagementsystem.database.LibraryDatabase
import com.testing.librarymanagementsystem.repository.AdminLoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val USERNAME = "user"
const val USERPASSWORD = "pass123"

class AdminSignUpViewModel(application: Context) : AndroidViewModel(application as Application) {

    private val appContext = application.applicationContext
    private val db = LibraryDatabase.getDB(appContext)
    private val adminLoginDao = db.adminLoginDao()
    private val adminLoginRepo = AdminLoginRepository(adminLoginDao)


    private val _adminLoginDetails = MutableStateFlow(emptyList<AdminLoginCredentialsModel>())
    val adminLoginDetails = _adminLoginDetails.asStateFlow()

    fun saveAdminLoginDetails(adminLoginCredentialsModel: AdminLoginCredentialsModel) {
        viewModelScope.launch {
            adminLoginRepo.addAdminDetails(adminLoginCredentialsModel)
        }

    }


    private var username: String = ""
    private var userpassword: String = ""

    var loginError by mutableStateOf("")

    private val _isLoginSuccess = mutableStateOf(false)
    val isLoginSuccess: State<Boolean> get() = _isLoginSuccess


    fun onUserNameChange(newUseName: String) {
        username = newUseName
        loginError = "" // Reset Errors when user types
    }

    fun onUserPasswordChange(newPassword: String) {
        userpassword = newPassword
        loginError = ""
    }

    var loginCred: List<AdminLoginCredentialsModel> = emptyList()


    fun clearCredentials() {
        _isLoginSuccess.value = false
        username = ""
        userpassword = ""
        loginError = ""
    }

    fun login() {
        val usernamePrefs = loginCred.first().adminUserName
        val userPasswordPrefs = loginCred.first().adminUserPassword
        if (username.isBlank() and userpassword.isBlank()) {
            loginError = "Username and Password can not be empty"
        } else if (username.isBlank() or userpassword.isBlank()) {
            loginError = "Username or Password can not be empty"
        } else
            viewModelScope.launch {
                if ((username != usernamePrefs) or (userpassword != userPasswordPrefs)) {
                    loginError = "Username or Password or both are incorrect"
                    //Handle success
                } else {
                    _isLoginSuccess.value = true

                }
            }
    }


    fun getAllList(valueUser: String, valuePassword: String) {
        viewModelScope.launch {
            adminLoginRepo.loginList(valueUser, valuePassword).collectLatest {
                val _list = _adminLoginDetails.tryEmit(it)
                val list = adminLoginDetails.value
                loginCred = list.ifEmpty {
                    listOf(
                        AdminLoginCredentialsModel(0, USERNAME, USERPASSWORD)
                    )
                }
                Log.d("TAG", "getAllList: list1$_list list2$list")

            }
        }
    }


    //Validation for the Signup Screen

    var signupUserName: String = ""
    var signupPassword: String = ""

    var errorMessage by mutableStateOf("")


    private var _isCredentialCorrect = mutableStateOf(false)
    val isCredentialCorrect: State<Boolean> get() = _isCredentialCorrect


    fun onSignupUserNameChanged(value: String) {
        signupUserName = value
        errorMessage = ""
    }

    fun onSignupPassword(value: String) {
        signupPassword = value
        errorMessage = ""
    }

    fun validateCredential() {
        if (signupUserName.isBlank()) {
            errorMessage = "User Name can't be empty"
        } else if (signupPassword.isBlank()) {
            errorMessage = "User Password can't be empty"
        } else {
            _isCredentialCorrect.value = true
        }
    }

    fun clearSignUpCredential(){

    }


}

