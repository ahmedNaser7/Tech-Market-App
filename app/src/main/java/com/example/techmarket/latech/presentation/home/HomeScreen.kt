package com.example.techmarket.latech.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.presentation.home.component.AdsList
import com.example.techmarket.latech.presentation.home.component.CategoryRow
import com.example.techmarket.latech.presentation.home.component.ProductsGridList


@Composable
fun HomeScreen(
    state: HomeState,
    onProductClick: (Product) -> Unit
) {

    if (state.isLoading){
        Box (
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(modifier = Modifier.size(40.dp), color = Color.Black)
        }
    }else {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Top Section: Header and Carousel
            Text(
                text = "Home",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Ads List
            AdsList(adsList = state.ads)

            // Category Buttons
            CategoryRow()

            // Sales Section
            Text(
                textAlign = TextAlign.Center,
                text = "Sales",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )


            // products
            ProductsGridList(state.products,onProductClick)
        }

    }

}










