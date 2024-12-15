package com.testing.librarymanagementsystem.feature.adminsignup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.CommonUI.CommonButton
import com.testing.librarymanagementsystem.util.CommonUI.CommonTextField
import com.testing.librarymanagementsystem.util.CustomShape
import com.testing.librarymanagementsystem.util.MainScreen
import com.testing.librarymanagementsystem.util.Screens
import com.testing.librarymanagementsystem.util.TextString

@Composable
fun SignUpScreen(onSignUp: () -> Unit, onBack: () -> Unit, navController: NavController) {

    val textString = TextString(
        startButtonString = stringResource(id = R.string.back),
        titleString = stringResource(id = R.string.please_sign_up_here)
    )

    MainScreen(onBack = onBack, onSave = onSignUp, textString = textString)
    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        Spacer(modifier = Modifier.size(85.dp))
        CommonTextField(
            label = stringResource(id = R.string.enter_username),
            shape = CommonUI.customShape(customShape = CustomShape.TopContent),
            value = "",
            onValueChange = {})
        Spacer(modifier = Modifier.size(1.dp))
        CommonTextField(
            label = stringResource(id = R.string.enter_userpassword),

            shape = CommonUI.customShape(customShape = CustomShape.BottomContent),
            value = "",
            onValueChange = {})
        Spacer(modifier = Modifier.size(20.dp))
        CommonButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = stringResource(id = R.string.submit_credentials),
            onClick = {
                navController.navigate(Screens.Login.route)
            })
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(onSignUp = {}, onBack = {}, navController = rememberNavController())
}

