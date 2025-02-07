package com.example.techmarket.latech.presentation.auth

import android.util.Log
import androidx.compose.foundation.background
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
import com.example.techmarket.core.navigation.Login
import com.example.techmarket.core.presentation.util.toString
import com.example.techmarket.latech.presentation.auth.components.TextEntryModule
import com.example.techmarket.latech.presentation.auth.components.viewModel.RegisterViewModel
import com.example.techmarket.ui.theme.onPrimaryContainerLight
import com.example.techmarket.ui.theme.yellowFontColor
import org.koin.androidx.compose.koinViewModel


@Preview
@Composable
private fun RegsiterScreenPreview() {
    RegisterScreen()
}


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
    navController: NavController = NavController(LocalContext.current)
) {



    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = onPrimaryContainerLight)
            .padding(horizontal = 15.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {

        Text(
            text = stringResource(R.string.Register),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 45.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(90.dp))

        RegisterContainer(
            userNameValue = {viewModel.registerState.usernameInput},
            emailValue = { viewModel.registerState.emailInput },
            addressValue = { viewModel.registerState.addressInput },
            passwordValue = { viewModel.registerState.passwordInput},
            onUserNameChange = viewModel::onUsernameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onAddressChange = viewModel::onAddressChange,
            isLoading = {viewModel.registerState.isLoading},
            enableButton = true,
            onRegisterButtonClick = {
               viewModel.register()
            },
            errorHint = viewModel.registerState.error?.toString(LocalContext.current) ?: "",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(10.dp))

        // loading the circle until finish Login
        if(viewModel.registerState.isLoading){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        if(viewModel.registerState.isSuccess){
            navController.navigate(Login)
        }

        if (viewModel.registerState.error != null) {
            Log.d("RegisterScreen", viewModel.registerState.error.toString())
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.already_have_an_account),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = yellowFontColor,
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun RegisterContainer(
    userNameValue: () -> String,
    emailValue: () -> String,
    passwordValue: () -> String,
    addressValue: () -> String,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    isLoading:()->Boolean,
    errorHint: String,
    enableButton: Boolean = true,
    onRegisterButtonClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // userNameField
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.username),
            hint = "Full name",
            textValue = userNameValue(),
            cursorColor = Color.Black,
            keyboardType = KeyboardType.Text,
            onValueChange = onUserNameChange
        )
        Spacer(modifier = Modifier.height(20.dp))
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

        // address Field
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.address),
            hint = "Address",
            textValue = addressValue(),
            cursorColor = Color.Black,
            keyboardType = KeyboardType.Text,
            onValueChange = onAddressChange
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
        Column {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = onRegisterButtonClick,
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
                    text = stringResource(R.string.Register),
                    fontWeight = FontWeight.Bold,
                    color = onPrimaryContainerLight,
                    textAlign = TextAlign.Start,
                    fontSize = 19.sp,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = errorHint,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                textAlign = TextAlign.Start,
                fontSize = 19.sp,
            )
        }
    }
}

