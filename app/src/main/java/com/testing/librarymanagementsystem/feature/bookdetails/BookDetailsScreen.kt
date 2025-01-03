package com.testing.librarymanagementsystem.feature.bookdetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.database.BookDetailModel
import com.testing.librarymanagementsystem.util.CommonUI.CommonButton
import com.testing.librarymanagementsystem.util.CommonUI.CommonText
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CommonUI.customShape
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.util.ParsingData
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.viewmodels.BookViewModel
import com.testing.librarymanagementsystem.viewmodels.CommonViewModelFactory
import org.json.JSONObject

@Composable
fun BookDetailsScreen(
    navController: NavController,
    onClick: () -> Unit,
    data: String
) {
    val context = LocalContext.current
    val bookViewModel: BookViewModel =
        viewModel(factory = CommonViewModelFactory(context.applicationContext))

    val jsonString: String = data
    Log.d("TAG", "newData: $data")

    val jsonObject =
        JSONObject(if (jsonString == "{itemlist}") ParsingData.defaultString else jsonString)
    val bookList = jsonObject.getJSONObject("booList")

    val uidString = bookList.getString("uid")
    val authNameString = bookList.getString("authNumber")
    val priceString = bookList.getString("price")
    val categoryString = bookList.getString("category")
    val editionString = bookList.getString("edition")
    val titleString = bookList.getString("title")
    val isbnString = bookList.getString("isbn")


    val bookDetailUpdate = BookDetailModel(
        uid = uidString.toInt(),
        authNumber = authNameString,
        price = priceString,
        category = categoryString,
        edition = editionString,
        title = titleString,
        isbn = isbnString
    )

    LaunchedEffect(key1 = Unit) {
        bookViewModel.getResponseModel(bookDetailUpdate)
    }


    var authNumber by remember {
        mutableStateOf(bookDetailUpdate.authNumber)
    }

    var price by remember {
        mutableStateOf(bookDetailUpdate.price)
    }
    var category by remember {
        mutableStateOf(bookDetailUpdate.category)
    }
    var edition by remember {
        mutableStateOf(bookDetailUpdate.edition)
    }
    var title by remember {
        mutableStateOf(bookDetailUpdate.title)
    }
    var isbn by remember {
        mutableStateOf(bookDetailUpdate.isbn)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .padding(
                    16.dp
                )
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CommonText(
                text = stringResource(id = R.string.enter_book_details),
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.size(20.dp))


            CommonTextField(
                value = bookViewModel.authNameState,
                onValueChange = { newText ->
                    authNumber = newText
                    bookViewModel.updateAuthText(newText)
                },
                label = stringResource(id = R.string.enter_author_name),
                shape = customShape(customShape = CustomShape.TopContent)
            )

            CommonTextField(
                value = bookViewModel.titleState,
                onValueChange = { newText ->
                    title = newText
                    bookViewModel.updateTitleText(newText)
                },
                label = stringResource(id = R.string.enter_title),
                shape = customShape(customShape = CustomShape.MiddleContent)
            )

            CommonTextField(
                value = bookViewModel.priceeState,
                onValueChange = { newText ->
                    price = bookViewModel.getValidatedNumber(newText)
                    bookViewModel.updatePriceText(price)
                },
                label = stringResource(id = R.string.enter_price),
                shape = customShape(customShape = CustomShape.MiddleContent),
                keyBoardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                prefixIcon = {
                    if (bookViewModel.priceeState.isNotBlank()) Icon(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                        contentDescription = ""
                    )
                }
            )

            CommonTextField(
                value = bookViewModel.categoryState,
                onValueChange = { newText ->
                    category = newText
                    bookViewModel.updateCategoryText(newText)

                },
                label = stringResource(id = R.string.enter_category),
                shape = customShape(customShape = CustomShape.MiddleContent)
            )

            CommonTextField(
                value = bookViewModel.editionState,
                onValueChange = { newText ->
                    edition = newText
                    bookViewModel.updateEditionText(newText)
                },
                label = stringResource(id = R.string.enter_edition),
                shape = customShape(customShape = CustomShape.MiddleContent),
                keyBoardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            CommonTextField(
                value = bookViewModel.isbnState,
                onValueChange = { newText ->
                    isbn = newText
                    bookViewModel.updateIsbnText(newText)
                },
                label = stringResource(id = R.string.enter_isbn),
                shape = customShape(customShape = CustomShape.BottomContent),
                keyBoardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            CommonButton(
                modifier = Modifier.padding(top = 30.dp),
                label = stringResource(id = R.string.submit_details),
                onClick = {
                    val book = BookDetailModel(
                        uid = bookDetailUpdate.uid,
                        authNumber = authNumber,
                        price = price,
                        category = category,
                        edition = edition,
                        title = title,
                        isbn = isbn
                    )
                    Log.d("TAG", "BookDetailsScreen1:${book}")
                    bookViewModel.saveBookDetails(
                        book.copy(
                            authNumber = authNumber,
                            price = price,
                            category = category,
                            edition = edition,
                            title = title,
                            isbn = isbn
                        )
                    )
                    navController.navigate(Screens.ShowAllBookDetails.route)

                })
        }
    }
}


@Preview
@Composable
fun BookDetailsScreenPreview() {
    BookDetailsScreen(navController = rememberNavController(), onClick = {}, "")
}
