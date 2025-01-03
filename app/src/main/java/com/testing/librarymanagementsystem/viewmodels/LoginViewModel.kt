//package com.testing.librarymanagementsystem.viewmodels
//
//import android.app.Application
//import android.content.Context
//import android.util.Log
//import androidx.compose.runtime.State
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
//import com.testing.librarymanagementsystem.database.LibraryDatabase
//import com.testing.librarymanagementsystem.repository.AdminLoginRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
//
//const val USERNAME = "user"
//const val USERPASSWORD = "pass123"
//
//class LoginViewModel(application: Context) : AndroidViewModel(application as Application) {
//    private val appContext = application.applicationContext
//    private val db = LibraryDatabase.getDB(appContext)
//    private val loginDao= db.adminLoginDao()
//    private val signupRepo = AdminLoginRepository(loginDao)
//
//    private var username: String = ""
//    private var userpassword: String = ""
//
//
//    private val _loginDetails = MutableStateFlow(emptyList<AdminLoginCredentialsModel>())
//    val loginDetails = _loginDetails.asStateFlow()
//
//
//    init {
//
//        viewModelScope.launch {
//            signupRepo.loginList.collectLatest {
//                _loginDetails.tryEmit(it)
//            }
//
//            Log.d("TAG", "$userDetail: ")
//        }
//
//
//    }
//
//    val usernamee :String = loginDetails.value.forEach{username}.toString()
//    val userpasswordd :String = loginDetails.value.forEach{username}.toString()
//
//    val userDetail = loginDetails.value.forEach {
//        it.apply {
//            adminUserName
//            adminUserPassword
//        }
//    }
//
//
//
//        var loginError by mutableStateOf("")
//
//        private val _isLoginSuccess = mutableStateOf(false)
//        val isLoginSuccess: State<Boolean> get() = _isLoginSuccess
//
//
//        fun onUserNameChange(newUseName: String) {
//            username = newUseName
//            loginError = "" // Reset Errors when user types
//        }
//
//        fun onUserPasswordChange(newPassword: String) {
//            userpassword = newPassword
//            loginError = ""
//        }
//
//
//        fun clearCredentials() {
//            _isLoginSuccess.value = false
//            username = ""
//            userpassword = ""
//            loginError = ""
//        }
//
//        fun login() {
//            if (username.isBlank() && userpassword.isBlank()) {
//                loginError = "Username and Password can not be empty"
//            } else
//                viewModelScope.launch {
//                    if (username != USERNAME && userpassword != USERPASSWORD && (username.isNotBlank() && userpassword.isNotBlank())) {
//                        loginError = "User name and password both are incorrect"
//                    } else if (username != USERNAME) {
//                        loginError = "User name is incorrect"
//                    } else if (userpassword != USERPASSWORD) {
//                        loginError = "User Password is incorrect"
//                        //Handle success
//                    } else {
//                        _isLoginSuccess.value = true
//
//                    }
//                }
//        }
//    }
