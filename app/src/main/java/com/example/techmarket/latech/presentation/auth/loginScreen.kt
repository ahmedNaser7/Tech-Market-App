package com.example.techmarket.latech.presentation.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techmarket.R
import com.example.techmarket.core.navigation.Register
import com.example.techmarket.latech.presentation.auth.components.TextEntryModule
import com.example.techmarket.latech.presentation.auth.components.viewModel.LoginViewModel
import com.example.techmarket.ui.theme.onPrimaryContainerLight
import com.example.techmarket.ui.theme.yellowFontColor
import org.koin.androidx.compose.koinViewModel


@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navController: NavController = NavController(LocalContext.current)
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = onPrimaryContainerLight)
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            text = stringResource(R.string.login),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 45.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(180.dp))

        LoginContainer(
            emailValue = { viewModel.loginState.emailInput },
            passwordValue = { viewModel.loginState.passwordInput },
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            isLoading = { viewModel.loginState.isLoading },
            enableButton = viewModel.loginState.emailInput.isNotBlank() && viewModel.loginState.passwordInput.isNotBlank(),
            onLoginButtonClick = {
                viewModel.loginWithEmail()
            },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(10.dp))

        // loading the circle until finish Login
        if (viewModel.loginState.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                viewModel.isUserAdmin()
                CircularProgressIndicator()
            }
        }

        if (viewModel.loginState.isSuccess && viewModel.loginState.isAdmin) {
            navController.navigate("Admin")
        }else if (viewModel.loginState.isSuccess&& !viewModel.loginState.isAdmin) {
            navController.navigate("Home")
        }


        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(R.string.Dont_have_an_account_Sign_up),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(Register)
                },
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = yellowFontColor,
        )

    }
}

@Composable
fun LoginContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isLoading: () -> Boolean,
    enableButton: Boolean = true,
    onLoginButtonClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email Field
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.email),
            hint = "Email",
            textValue = emailValue(),
            cursorColor = Color.Black,
            keyboardType = KeyboardType.Email,
            onValueChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Password Field
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.password),
            hint = "Password",
            textValue = passwordValue(),
            cursorColor = Color.Black,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = onPasswordChange
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = onLoginButtonClick,
            enabled = enableButton,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            )
        ) {
            Text(
                text = stringResource(R.string.login),
                fontWeight = FontWeight.Bold,
                color = onPrimaryContainerLight,
                textAlign = TextAlign.Start,
                fontSize = 19.sp,
            )
        }

    }
}

