package com.example.composable.ui.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composable.model.identity.UserInfo


@Composable
fun AppScreen(userInfo: UserInfo) {
    if (!userInfo.userLoggedIn) {
        LoginScreen(userInfo)
    } else {
        UserInfoScreen(userInfo)
    }
}

@Composable
fun UserInfoScreen(userInfo: UserInfo) {
    Column(modifier = Modifier.padding(all = 8.dp)) {
        Text(
            text = "Username: ${userInfo.userName}",
            modifier = Modifier.padding(all = 8.dp),
            style = (MaterialTheme.typography).h6
        )

    }
}

@Composable
fun LoginScreen(userInfo: UserInfo = UserInfo()) {
    Column {
        val userNameState = mutableStateOf("")
        Text(
            text = "Username:",
            modifier = Modifier.padding(all = 8.dp),
            style = (MaterialTheme.typography).h4
        )
        Surface(modifier = Modifier
            .border(1.dp, Color.Gray)
            .padding(all = 8.dp)) {
            TextField(
                value = userNameState.value,
                onValueChange = { userNameState.value = it }
            )
        }

        val passwordState = mutableStateOf("")
        var passwordVisibility by remember { mutableStateOf(false) }
        TextField(
            value = "Password:",
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Toggle show")
                }
            },
            onValueChange = { },
            modifier = Modifier.padding(all = 8.dp)
        )
        Surface(Modifier
            .border(1.dp, Color.Gray)
            .padding(all = 8.dp)) {
            TextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it }
            )
        }

        if (userNameState.value.isNotEmpty()
            && passwordState.value.isNotEmpty()
        ) {
            Row(Modifier.align(Alignment.End)) {
                Button(
                    content = { Text("Login") },
                    modifier = Modifier.padding(all = 8.dp),
                    onClick = {
                        println("Logged in!")
                        userInfo.userName = userNameState.value
                        userInfo.userLoggedIn = true
                    }
                )
            }
        } else {
            Text(
                text = "Please enter both username and password!",
                modifier = Modifier.padding(all = 8.dp),
                style = (MaterialTheme.typography).h6
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        LoginScreen()
    }
}