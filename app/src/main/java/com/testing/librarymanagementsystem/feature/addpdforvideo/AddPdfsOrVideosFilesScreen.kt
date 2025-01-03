package com.testing.librarymanagementsystem.feature.addpdforvideo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.feature.adminsignup.AdminSignUpViewModel
import com.testing.librarymanagementsystem.util.CommonUI
import com.testing.librarymanagementsystem.util.MainScreen
import com.testing.librarymanagementsystem.util.TextString
import com.testing.librarymanagementsystem.viewmodels.CommonViewModelFactory

@Composable
fun AddPdfsAndVideosScreen(onBack: () -> Unit) {
    val textString = TextString(
        startButtonString = stringResource(id = R.string.back),
        titleString = stringResource(id = R.string.upload_pdfs_videos_here)
    )
    val context = LocalContext.current
    val signUpViewModel: AdminSignUpViewModel =
        viewModel(factory = CommonViewModelFactory(context.applicationContext))

    var uploadPdfs by remember {
        mutableStateOf("")
    }

    var uploadVideosLinks by remember {
        mutableStateOf("")
    }


    MainScreen(onBack = onBack, onSave = {}, textString = textString)
    Column(
        modifier = Modifier.padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(65.dp))

        CommonUI.CommonText(text = R.string.upload_pdfs.toString())
        CommonUI.CommonText(text = R.string.upload_videos.toString())
    }
}
