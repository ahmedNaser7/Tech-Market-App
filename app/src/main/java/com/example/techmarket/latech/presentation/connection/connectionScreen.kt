package com.example.techmarket.latech.presentation.connection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techmarket.R
import com.example.techmarket.core.navigation.Login
import com.example.techmarket.latech.presentation.connection.components.ButtonType
import com.example.techmarket.latech.presentation.connection.components.ConnectionState
import com.example.techmarket.latech.presentation.connection.components.ConnectionButton
import com.example.techmarket.ui.theme.onPrimaryContainerLight


@Composable
fun ConnectionScreen(
    navController: NavController,
    state:ConnectionState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = onPrimaryContainerLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(modifier =Modifier.size(40.dp), color = Color.White)
            }
        }else if(state.isLogged){
            navController.navigate("Home") {
                popUpTo(0)
            }
        }
        else {
            Text(
                text = stringResource(R.string.connection),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(60.dp))

            ConnectionButton(
                ButtonType.EMAIL,
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                R.string.customer_login, onPrimaryContainerLight
            ) {
                navController.navigate(Login) {
                    popUpTo(0)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            ConnectionButton(
                ButtonType.EMAIL,
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                R.string.admin_login,
                onPrimaryContainerLight
            ) {
                navController.navigate("Admin") {
                    popUpTo(0)
                }
            }

//        TechMarketConnectionButton(ButtonType.GOOGLE,Modifier.fillMaxWidth().padding(horizontal = 30.dp),R.string.Connect_with_google,Color.Gray)
//        Spacer(modifier = Modifier.height(10.dp))
//        TechMarketConnectionButton(ButtonType.FACEBOOK,Modifier.fillMaxWidth().padding(horizontal = 30.dp),R.string.Connect_with_facebook, Color.White, facebookButtonColor)
//        Spacer(modifier = Modifier.height(30.dp))
//        Text(
//            modifier = Modifier.clickable {
//                navController.navigate(Login){
//                    popUpTo(0)
//                }
//            },
//            text = stringResource(R.string.already_have_an_account),
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            color = yellowFontColor,
//        )
        }
    }
}

//@PreviewScreenSizes
//@Composable
////private fun Preview() {
////    ConnectionScreen(ConnectionViewModel(),NavController(LocalContext.current))
////}

