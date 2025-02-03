package com.example.techmarket.latech.presentation.onBoarding.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.R
import com.example.techmarket.ui.theme.onPrimaryContainerLight


@PreviewLightDark
@Composable
private fun PreviewPage() {
    OnBoardingPage(modifier = Modifier, pageIndex = 2 )
}


@Composable
fun OnBoardingPage(
    pageIndex: Int,
    modifier: Modifier = Modifier
) {

    when (pageIndex) {
        0 -> OnBoardingPageContent(pageIndex,Modifier)
        1 -> OnBoardingPageContent(pageIndex,Modifier)
        2 -> OnBoardingPageContent(pageIndex,Modifier)
    }



}

@Composable
fun OnBoardingPageContent(
    pageIndex: Int,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(onPrimaryContainerLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (pageIndex) {
            0 ->  PageContent(imageModifier = Modifier.padding(top = 80.dp),textModifier = Modifier.padding(top = 150.dp), imageRes = R.drawable.network, title = R.string.the_best_tech_market)
            1 ->  PageContent(imageModifier = Modifier.fillMaxWidth(), alignment = Alignment.TopStart, imageRes = R.drawable.computer, title = R.string.a_lot_of_exclusives)
            2 ->  PageContent(imageModifier = Modifier.fillMaxWidth().align(Alignment.End), alignment = Alignment.TopEnd, textModifier = Modifier.padding(top = 85.dp), imageRes = R.drawable.discount, title = R.string.Sales_all_the_time)
        }
        

    }
}

@Composable
fun PageContent(
    alignment: Alignment=Alignment.Center,
    textModifier: Modifier=Modifier,
    imageModifier: Modifier=Modifier,
    imageRes: Int,
    title: Int,
) {
    val visibility by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visibility,
        enter = slideInVertically {
            with(density) { +40.dp.roundToPx() }
        } + expandHorizontally(expandFrom = Alignment.Start)
                + fadeIn(initialAlpha = 3f),
        exit = slideOutHorizontally() + shrinkVertically() + fadeOut()
    ) {
        Image(
            modifier = imageModifier,
            alignment = alignment,
            contentScale = ContentScale.Fit,
            painter = painterResource(imageRes),
            contentDescription = ""
        )
    }

    Text(
        modifier = textModifier,
        text = stringResource(title),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
}



