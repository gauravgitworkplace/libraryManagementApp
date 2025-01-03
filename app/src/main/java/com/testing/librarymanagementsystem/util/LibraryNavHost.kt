package com.testing.librarymanagementsystem.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.testing.librarymanagementsystem.feature.adminsignup.SignUpScreen
import com.testing.librarymanagementsystem.feature.bookdetails.BookDetailsScreen
import com.testing.librarymanagementsystem.feature.login.LoginScreen
import com.testing.librarymanagementsystem.feature.searchscreeen.SearchBookScreen
import com.testing.librarymanagementsystem.feature.showbooklist.ShowAllBooksListScreen
import com.testing.librarymanagementsystem.feature.splash.SplashScreen

@Composable
fun LibraryNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Splash.route) { SplashScreen(onTimeout = {}) }
        composable(Screens.Login.route) {
            LoginScreen(
                navController = navController,
                onClick = { }
            )
        }
        composable(Screens.BookDetails.route) { backStackEntry ->
            val data = backStackEntry.arguments?.getString("itemlist")
            BookDetailsScreen(
                navController = navController,
                onClick = {},
                data ?: ParsingData.defaultString
            )
        }
        composable(Screens.ShowAllBookDetails.route) {
            ShowAllBooksListScreen(
                navController = navController,
                ""
            )
        }
        composable(Screens.SearchedBookList.route) { SearchBookScreen(navController = navController) }
        composable(Screens.AdminSignUp.route) {
            SignUpScreen(
                onSignUp = {},
                onBack = {navController.popBackStack()},
                navController = navController
            )
        }
    }
}
