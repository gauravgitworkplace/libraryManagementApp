package com.testing.librarymanagementsystem.feature.searchscreeen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.feature.showbooklist.ImmutableList
import com.testing.librarymanagementsystem.feature.showbooklist.ShowBookDetails
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.CommonUI.CommonText
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.viewmodels.CommonViewModelFactory


@Composable
fun SearchBookScreen(navController: NavController) {
    val context = LocalContext.current
    var titleAutherOrSubjext by remember {
        mutableStateOf("")
    }

    val searchBooksViewModel: SearchBooksViewModel =
        viewModel(factory = CommonViewModelFactory(context.applicationContext))

    val bookList by searchBooksViewModel.searchBookLists.collectAsStateWithLifecycle()

    LaunchedEffect(titleAutherOrSubjext) {
        searchBooksViewModel.getAllListDetails(titleAutherOrSubjext)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CommonText(
                text = stringResource(id = R.string.search_books),
                textColor = Color.Blue,
                style = MaterialTheme.typography.titleLarge
            )
            CommonTextField(
                label = stringResource(id = R.string.enter_book_title_or_auther_name_or_subject_name),
                value = titleAutherOrSubjext,
                onValueChange = {
                    titleAutherOrSubjext = it
                    searchBooksViewModel.getValueForSearched(it)
                },
                shape = CommonUI.customShape(customShape = CustomShape.DEFAULT)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(vertical = 3.dp, horizontal = 1.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                CommonText(
                    modifier = Modifier
                        .weight(15f),
                    text = stringResource(id = R.string.s_no),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    textColor = Color.Magenta
                )
                CommonText(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(30f),
                    text = stringResource(id = R.string.book_title),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    textColor = Color.Blue

                )
                CommonText(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(30f),
                    text = stringResource(id = R.string.author_name),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    textColor = Color.Black

                )
                CommonText(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .weight(20f),
                    text = stringResource(id = R.string.subject),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    textColor = Color.Red

                )
            }

            LazyColumn(content = {
                items(bookList) {
                    val item = ImmutableList(it)
                    Log.d("TAG", "SearchBookScreen: $bookList")
                    ShowBookDetails(wrapper = item,
                        onDeleteBook = {},
                        onUpdateBookDetails =
                        {
                        })
                }

            })
        }
    }

}


@Composable
@Preview
fun SearchPreviewScreen() {
    SearchBookScreen(navController = rememberNavController())
}