package com.example.techmarket.latech.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.R
import com.example.techmarket.ui.theme.onPrimaryContainerLight

// not using for curr project

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(onPrimaryContainerLight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.latech),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Text(
            text = stringResource(R.string.tech_market),
            modifier = modifier.padding(top = 4.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Light,
            color = Color.White,
        )

        TechMarketIcon(modifier.padding(top = 20.dp, bottom = 20.dp))

        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 50.dp),
            onClick = { /*TODO*/ },
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = onPrimaryContainerLight,
                disabledContainerColor = Color.Black,
                disabledContentColor = onPrimaryContainerLight
            ),
            shape = RoundedCornerShape(5.dp),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = stringResource(R.string.lets_start),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = onPrimaryContainerLight,
            )
        }

        Text(
            modifier = Modifier.padding(top = 80.dp),
            text = stringResource(R.string.skip_for_now),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

    }
}



@Composable
fun TechMarketIcon(modifier: Modifier ) {
    Box(
        modifier = modifier
            .size(230.dp)
            .shadow(
                elevation = 11.dp,
                shape = RoundedCornerShape(150.dp),
                ambientColor = Color.White,
                spotColor = Color.White
            )
            .shadow(
                elevation = 17.dp,
                shape = RoundedCornerShape(200.dp),
                ambientColor = Color.White,
                spotColor = Color.White
            )
            .background(color = onPrimaryContainerLight),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(150.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Logo"
        )
    }
}
