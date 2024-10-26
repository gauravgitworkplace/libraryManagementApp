package com.testing.librarymanagementsystem.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.librarymanagementsystem.models.LoginModel
import kotlinx.coroutines.launch


const val USERNAME = "user"
const val USERPASSWORD = "pass123"

class LoginViewModel : ViewModel() {

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
     fun clearCredentials() {
        _isLoginSuccess.value = false
        username = ""
         userpassword = ""
        loginError = ""
    }

    fun login() {
        if (username.isBlank() && userpassword.isBlank()) {
            loginError = "Username and Password can not be empty"
        } else
            viewModelScope.launch {
                if (username != USERNAME && userpassword != USERPASSWORD && (username.isNotBlank() && userpassword.isNotBlank())) {
                    loginError = "User name and password both are incorrect"
                } else if (username != USERNAME) {
                    loginError = "User name is incorrect"
                } else if (userpassword != USERPASSWORD) {
                    loginError = "User Password is incorrect"
                    //Handle success
                } else {
                    _isLoginSuccess.value = true

                }
            }
    }
}
