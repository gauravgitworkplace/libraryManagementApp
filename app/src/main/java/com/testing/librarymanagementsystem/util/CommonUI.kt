package com.testing.librarymanagementsystem.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.testing.librarymanagementsystem.R

object CommonUI {

    @Composable
    fun customShape(customShape: CustomShape) = when (customShape) {
        CustomShape.DEFAULT -> MaterialTheme.shapes.small
        CustomShape.TopContent -> MaterialTheme.shapes.small.copy(
            bottomStart = CornerSize(0.dp), bottomEnd = CornerSize(0.dp)
        )

        CustomShape.MiddleContent -> MaterialTheme.shapes.small.copy(
            all = CornerSize(0.dp)
        )

        CustomShape.BottomContent -> MaterialTheme.shapes.small.copy(
            topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp)
        )
    }

    @Composable
    fun CommonText(
        modifier: Modifier = Modifier,
        text: String,
        textColor: Color = MaterialTheme.colorScheme.background,
        style: TextStyle = MaterialTheme.typography.bodySmall,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            modifier = modifier,
            text = text,
            color = textColor,
            textAlign = textAlign,
            style = style,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    @Composable
    fun CommonTextField(
        modifier: Modifier = Modifier,
        label: String,
        value: String,
        onValueChange: (String) -> Unit,
        singleLine: Boolean = true,
        isError: Boolean = false,
        shape: CornerBasedShape = customShape(customShape = CustomShape.MiddleContent),
        keyPadOptions: KeyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
        ),
        prefixIcon: @Composable (() -> Unit)? = null
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 1.dp),
            value = value.toString(),
            onValueChange = {
                onValueChange(it)
            },
            singleLine = singleLine,
            shape = shape,
            placeholder = {
                label.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            keyboardOptions = keyPadOptions,
            prefix = prefixIcon
        )
    }

    @Composable
    fun CommonButton(
        modifier: Modifier = Modifier,
        label: String,
        shape: CornerBasedShape = MaterialTheme.shapes.small,
        color: ButtonColors = ButtonColors(Color.LightGray, Color.Black, Color.White, Color.White),
        onClick: () -> Unit,
        style: TextStyle = MaterialTheme.typography.titleMedium
    ) {
        Button(
            modifier = modifier,
            shape = shape,
            colors = color,
            onClick = onClick
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(3.dp),
                style = style
            )
        }
    }

    @Composable
    fun UpdateBookDetailsDialog(
        onDismiss: () -> Unit,
        onDelete: () -> Unit,
        onUpdateBookDetails: () -> Unit
    ) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 8.dp) {

                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Magenta
                        )
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CommonText(
                        text = stringResource(id = R.string.perform_actions),
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.Magenta)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CommonButton(
                            label = stringResource(id = R.string.edit),
                            onClick = {
                                onDismiss()
                                onUpdateBookDetails()
                            },
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Magenta)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        CommonButton(
                            label = stringResource(id = R.string.delete),
                            onClick = {
                                onDismiss()
                                onDelete()
                            },
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Magenta)
                        )
                    }
                }
            }

        }
    }
}

enum class CustomShape {
    DEFAULT,
    TopContent,
    MiddleContent,
    BottomContent
}