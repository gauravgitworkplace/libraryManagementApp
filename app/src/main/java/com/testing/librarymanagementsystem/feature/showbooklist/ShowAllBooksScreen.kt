package com.testing.librarymanagementsystem.feature.showbooklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.google.gson.Gson
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.database.BookDetailModel
import com.testing.librarymanagementsystem.util.CommonUI.CommonText
import com.testing.librarymanagementsystem.util.CommonUI.UpdateBookDetailsDialog
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.viewmodels.BookViewModel
import com.testing.librarymanagementsystem.viewmodels.MyViewModelFactory

@Composable
fun ShowAllBooksListScreen(navController: NavController, itemList: String) {
    val context = LocalContext.current
    val bookViewModel: BookViewModel =
        viewModel(factory = MyViewModelFactory(context.applicationContext))


    val bookList by bookViewModel.bookLists.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(vertical = 3.dp, horizontal = 1.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(5f).clickable { navController.navigate(Screens.SearchedBookList.route) },
                    painter = painterResource(id = R.drawable.ic_search_24),
                    contentDescription = null,
                    tint = Color.White
                )
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
                    ShowBookDetails(wrapper = item,
                        onDeleteBook = { bookViewModel.deleteBookList(it) },
                        onUpdateBookDetails =
                        {
                            val bookDetailModelJson = Gson().toJson(item)
                            navController.navigate("bookDetailsScreen/$bookDetailModelJson")
                        })
                }

            })
        }
    }

}

@Composable

fun ShowBookDetails(
    wrapper: ImmutableList,
    onDeleteBook: () -> Unit,
    onUpdateBookDetails: () -> Unit
) {


    var showDialog by remember {
        mutableStateOf(false)
    }

    Spacer(modifier = Modifier.size(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val style = MaterialTheme.typography.titleSmall
        val textAlign = TextAlign.Justify


        if (showDialog) {
            UpdateBookDetailsDialog(
                onDismiss = { showDialog = false },
                onDeleteBook,
                onUpdateBookDetails
            )
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(5f)
                .clickable(onClick = {
                    showDialog = true

                }),
            painter = painterResource(id = R.drawable.baseline_edit_24),
            contentDescription = null,
            tint = Color.Red
        )

        CommonText(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .weight(15f),
            text = wrapper.booList.uid.toString(),
            style = style, textAlign = textAlign,
            textColor = Color.Magenta

        )
        CommonText(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .weight(30f),
            text = wrapper.booList.title,
            style = style, textAlign = textAlign,
            textColor = Color.Blue

        )
        CommonText(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .weight(30f),
            text = wrapper.booList.authNumber,
            style = style,
            textAlign = textAlign,
            textColor = Color.Black

        )
        CommonText(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .weight(20f),
            text = wrapper.booList.category,
            style = style,
            textAlign = textAlign,
            textColor = Color.Red

        )
    }
}

@Immutable
data class ImmutableList(val booList: BookDetailModel)

@Preview
@Composable
fun ShowAllBooksDetailsScreenPreview() {

    ShowAllBooksListScreen(navController = rememberNavController(), "")

}
