package com.example.techmarket.latech.presentation.onBoarding

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techmarket.R
import com.example.techmarket.core.navigation.Login
import com.example.techmarket.latech.presentation.onBoarding.components.CustomPageIndicators
import com.example.techmarket.latech.presentation.onBoarding.components.OnBoardingPage
import com.example.techmarket.latech.presentation.onBoarding.components.state.OnBoardingState
import com.example.techmarket.ui.theme.onPrimaryContainerLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(
    navController: NavController,
    state: OnBoardingState,
    modifier: Modifier = Modifier
) {
    Log.d("OnBoarding",state.toString())

    if (state.isLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if(!state.networkStatue){
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No Internet Connection", fontSize = 32.sp)
        }

    }else if (state.isLogged) {
        if (!state.isAdmin) navController.navigate("Home")
        else navController.navigate("Admin")
    } else {
        val pagerState = rememberPagerState { 3 }
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(onPrimaryContainerLight)
        ) {
            HorizontalPager(
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> OnBoardingPage(0)
                    1 -> OnBoardingPage(1)
                    2 -> OnBoardingPage(2)
                }
            }
            CustomPageIndicators(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 174.dp)
                    .padding(bottom = 70.dp),
                pageCount = 3,
                currentPage = pagerState.currentPage
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                val coroutineScope = rememberCoroutineScope()
                Text(
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            delay(100L)
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1,
                                animationSpec = tween(
                                    durationMillis = 200, // Control the speed of the animation
                                    easing = LinearEasing // Define the easing for smoothness
                                )
                            )
                        }
                        // nav to register after last screen
                        if (pagerState.currentPage == 2)
                            navController.navigate(Login)
                    },
                    text = stringResource(R.string.next),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }

        }
    }
}

@PreviewScreenSizes
@Composable
private fun Preview() {
    OnBoardingScreen(
        NavController(LocalContext.current),
        OnBoardingState(isLoading = false, networkStatue = false)
    )
}

