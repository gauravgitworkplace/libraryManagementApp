package com.testing.librarymanagementsystem.util

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Login : Screens("login")
    data object BookDetails : Screens("bookDetailsScreen/{itemlist}")
    data object ShowAllBookDetails : Screens("showBookDetailsScreen")
    data object SearchedBookList : Screens("searchedBookList")

}