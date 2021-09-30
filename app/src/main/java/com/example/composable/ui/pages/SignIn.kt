package com.example.composable.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composable.model.identity.UserInfo
import com.example.composable.ui.theme.Blue700


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
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            val userNameState = remember { mutableStateOf(TextFieldValue()) }
            val passwordState = remember { mutableStateOf(TextFieldValue()) }
            var passwordVisibility by remember { mutableStateOf(false) }

            Text(
                text = "Email",
                modifier = Modifier.padding(all = 8.dp)
            )
            TextField(
                value = userNameState.value,
                onValueChange = { userNameState.value = it }
            )

            Text(
                text = "Password",
                modifier = Modifier.padding(all = 8.dp)
            )
            TextField(
                value = passwordState.value,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {  passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "Toggle show")
                    }
                },
                onValueChange = { passwordState.value = it },
                modifier = Modifier.padding(all = 4.dp)
            )

            Button(
                content = { Text("Login") },
                modifier = Modifier
                    .padding(all = 4.dp)
                    .width(280.dp)
                    .height(56.dp),
                onClick = {
                    println("Logged in!")
                    userInfo.userName = userNameState.value.toString()
                    userInfo.userLoggedIn = true
                }
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