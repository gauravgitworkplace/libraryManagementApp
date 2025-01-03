package com.testing.librarymanagementsystem.feature.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
import com.testing.librarymanagementsystem.feature.adminsignup.AdminSignUpViewModel
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.CommonUI.CommonButton
import com.testing.librarymanagementsystem.util.CommonUI.CommonText
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.viewmodels.CommonViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    val signUpViewModel: AdminSignUpViewModel =
        viewModel(factory = CommonViewModelFactory(context.applicationContext))

    val bookList by signUpViewModel.adminLoginDetails.collectAsStateWithLifecycle()

    var userName by remember { mutableStateOf("") }
    var userpassword by remember { mutableStateOf("") }
    var isError by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.FillBounds

        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            signUpViewModel.loginError.let { error ->
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            LaunchedEffect(userName, userpassword) {
                signUpViewModel.getAllList(userName, userpassword)
            }

            Spacer(modifier = Modifier.size(20.dp))

            CommonTextField(
                value = userName,
                onValueChange = {
                    userName = it
                    isError = userName.length > 10
                    signUpViewModel.onUserNameChange(it)
                },
                isError = isError,
                shape = CommonUI.customShape(customShape = CustomShape.TopContent),
                label = stringResource(id = R.string.enter_username)
            )


            LazyColumn(content = {
                items(bookList) {
                    val item = ImmutableList(it)
                    Log.d("TAG", "LoginList: $item")
                }
            })

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userpassword,
                singleLine = true,
                onValueChange = {
                    userpassword = it
                    signUpViewModel.onUserPasswordChange(it)

                },
                visualTransformation = PasswordVisualTransformation(),
                shape = ShapeDefaults.Small.copy(
                    topStart = CornerSize(0.dp),
                    topEnd = CornerSize(0.dp)
                ),

                placeholder = {
                    Text(
                        text = stringResource(id = R.string.enter_userpassword),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent, // Color when disabled
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.size(30.dp))
            CommonButton(
                modifier = Modifier
                    .wrapContentWidth(),
                label = stringResource(id = R.string.login_here),
                shape = MaterialTheme.shapes.small,
                color = ButtonColors(Color.LightGray, Color.Black, Color.White, Color.White),
                onClick = {

                    signUpViewModel.login()
                    if (signUpViewModel.isLoginSuccess.value) {
                        navController.navigate(Screens.BookDetails.route)
                        signUpViewModel.clearCredentials()
                    } else {
                        onClick()
                    }
                })


            Spacer(modifier = Modifier.size(50.dp))

            CommonUI.CommonText(text = stringResource(id = R.string.if_you_new_user_please_click_on_signup_here))
            Spacer(modifier = Modifier.size(5.dp))
            CommonText(
                text = stringResource(id = R.string.signup_here),
                modifier = Modifier.clickable {
                    navController.navigate(Screens.AdminSignUp.route)
                }, textColor = Color.Blue
            )
        }
    }
}

@Immutable
data class ImmutableList(val list: AdminLoginCredentialsModel)

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        onClick = { /*TODO*/ }
    )
}