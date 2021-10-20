package com.example.composable.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composable.model.identity.UserInfo
import com.example.composable.ui.theme.Shapes
import com.example.composable.viewModel.AuthenticationViewModel


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
                    AuthenticationViewModel().onClickSignin()
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SignInButton(
    text: String,
    loadingText: String = "Signing in...",
    icon: Painter,
    isLoading: Boolean = false,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable(
            enabled = !isLoading,
            onClick = onClick
        ),
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = icon,
                contentDescription = "SignInButton",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(text = if (isLoading) loadingText else text)
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
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