package com.example.composable.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.composable.R
import com.example.composable.ui.theme.Grey100
import com.example.composable.ui.theme.Purple200


@Composable
fun CustomSearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    searchFocused: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            decorationBox = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Grey100, RoundedCornerShape(percent = 30))
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = Color.DarkGray,
                            contentDescription = stringResource(R.string.nav_search)
                        )
                    }

                    searching = if (value.isEmpty()) {
                        Text(stringResource(R.string.nav_search), modifier = Modifier.weight(1f))
                        false
                    } else {
                        Text(value, modifier = Modifier.weight(1f))
                        true
                    }

                    if (searching) {
                        CircularProgressIndicator(
                            color = Purple200,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(36.dp)
                        )
                    } else {
                        Spacer(Modifier.width(ButtonDefaults.IconSize)) // balance arrow icon
                    }
                }
            }
        )
    }
}