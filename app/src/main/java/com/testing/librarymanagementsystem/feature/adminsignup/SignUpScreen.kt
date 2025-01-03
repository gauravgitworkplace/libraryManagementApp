package com.testing.librarymanagementsystem.feature.adminsignup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.database.AdminLoginCredentialsModel
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.CommonUI.CommonButton
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.util.MainScreen
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.util.TextString
import com.testing.librarymanagementsystem.viewmodels.CommonViewModelFactory

@Composable
fun SignUpScreen(onSignUp: () -> Unit, onBack: () -> Unit, navController: NavController) {
    val context = LocalContext.current

    val textString = TextString(
        startButtonString = stringResource(id = R.string.back),
        titleString = stringResource(id = R.string.please_sign_up_here)
    )

    val signUpViewModel: AdminSignUpViewModel =
        viewModel(factory = CommonViewModelFactory(context.applicationContext))

    var username by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    val adminLoginDetailsList by signUpViewModel.adminLoginDetails.collectAsStateWithLifecycle()

    MainScreen(onBack = onBack, onSave = onSignUp, textString = textString)
    Column(
        modifier = Modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(65.dp))

        val error: String = signUpViewModel.errorMessage
        CommonUI.CommonText(
            text = error.ifBlank { "" },
            textColor = Color.Red,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(20.dp))
        CommonTextField(
            label = stringResource(id = R.string.enter_username),
            shape = CommonUI.customShape(customShape = CustomShape.TopContent),
            value = username,
            onValueChange = {
                username = it
                signUpViewModel.onSignupUserNameChanged(it)
            })
        Spacer(modifier = Modifier.size(1.dp))
        CommonTextField(
            label = stringResource(id = R.string.enter_userpassword),

            shape = CommonUI.customShape(customShape = CustomShape.BottomContent),
            value = userPassword,
            onValueChange = {
                userPassword = it
                signUpViewModel.onSignupPassword(it)
            },
            keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Done)

        )
        Spacer(modifier = Modifier.size(20.dp))
        CommonButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = stringResource(id = R.string.submit_credentials),
            onClick = {
                signUpViewModel.validateCredential()
                if (signUpViewModel.isCredentialCorrect.value) {
                    signUpViewModel.saveAdminLoginDetails(
                        AdminLoginCredentialsModel(
                            0,
                            username,
                            userPassword
                        )
                    )
                    navController.navigate(Screens.Login.route)
                }
            })
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(onSignUp = {}, onBack = {}, navController = rememberNavController())
}

