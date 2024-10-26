package com.testing.librarymanagementsystem.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    loginViewModel: LoginViewModel
) {
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

            loginViewModel.loginError.let { error ->
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            CommonTextField(
                value = userName,
                onValueChange = {
                    userName = it
                    isError = userName.length > 10
                    loginViewModel.onUserNameChange(it)
                },
                isError = isError,
                shape = CommonUI.customShape(customShape = CustomShape.TopContent),
                label = stringResource(id = R.string.enter_username)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = userpassword,
                singleLine = true,
                onValueChange = {
                    userpassword = it
                    loginViewModel.onUserPasswordChange(it)
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
            )
            Button(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 30.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonColors(Color.LightGray, Color.Black, Color.White, Color.White),
                onClick = {
                    onClick()
                    if (loginViewModel.isLoginSuccess.value) {
                        navController.navigate(Screens.BookDetails.route)
                        loginViewModel.clearCredentials()
                    }
                }) {
                Text(
                    stringResource(id = R.string.login_here),
                    modifier = Modifier.padding(3.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        onClick = { /*TODO*/ },
        loginViewModel = LoginViewModel()
    )
}