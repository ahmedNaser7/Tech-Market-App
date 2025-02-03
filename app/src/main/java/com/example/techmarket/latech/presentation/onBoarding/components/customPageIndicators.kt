package com.example.techmarket.latech.presentation.onBoarding.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomPageIndicators(modifier: Modifier, pageCount: Int, currentPage: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        for (i in 0 until pageCount) {
            val size by animateDpAsState(
                targetValue = if (i == currentPage) 12.dp else 8.dp, // animate size based on selection
                animationSpec = tween(durationMillis = 300), label = ""
            )
            val color by animateColorAsState(
                targetValue = if (i == currentPage) Color.White else Color.Gray, // animate color based on selection
                animationSpec = tween(durationMillis = 300), label = ""
            )

            Box(
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
