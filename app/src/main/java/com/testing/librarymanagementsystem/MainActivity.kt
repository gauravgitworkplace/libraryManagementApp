package com.testing.librarymanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.feature.adminsignup.SignUpScreen
import com.testing.librarymanagementsystem.feature.login.LoginScreen
import com.testing.librarymanagementsystem.feature.splash.SplashScreen
import com.testing.librarymanagementsystem.ui.theme.LibraryManagementSystemTheme
import com.testing.librarymanagementsystem.util.LibraryNavHost
import com.testing.librarymanagementsystem.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyLibraryApp(navController)
        }
    }

    @Composable
    fun HandleSplashScreen(navController: NavController) {
        var isSplashVisible by remember {
            mutableStateOf(true)
        }
        if (isSplashVisible) {
            SplashScreen(onTimeout = { isSplashVisible = false })
        } else {
            SignUpScreen(onSignUp = {}, onBack = { }, navController = navController)
        }
    }

    @Composable
    fun MyLibraryApp(navController: NavHostController) {
        LibraryManagementSystemTheme {
            HandleSplashScreen(navController)
            LibraryNavHost(navController = navController, loginViewModel = loginViewModel)
        }
    }
}