package com.testing.librarymanagementsystem.util

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.testing.librarymanagementsystem.R
import com.testing.librarymanagementsystem.util.CommonUI.CommonText

@Composable
fun MainScreen(onBack: () -> Unit, onSave: () -> Unit, textString: TextString) {

    CommonScreen(onBack = onBack, onSave = onSave, textString = textString)
}


@Composable
fun CommonScreen(onBack: () -> Unit, onSave: () -> Unit, textString: TextString) {

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
                .padding(horizontal = 15.dp)
                .padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                CommonText(
                    text = textString.startButtonString,
                    modifier = Modifier.clickable { onBack() })
                textString.endButtonText?.let {
                    CommonText(
                        text = it,
                        modifier = Modifier.clickable { onSave() })
                }
            }
        }
        Box {
            CommonText(
                text = textString.titleString,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
            )
        }

        Spacer(modifier = Modifier.size(3.dp))
        if (textString.subTitleString != null) {
            CommonText(text = textString.subTitleString)
        }
    }

}

data class TextString(
    val startButtonString: String,
    val endButtonText: String? = null,
    val titleString: String,
    val subTitleString: String? = null
)