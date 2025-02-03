package com.example.techmarket.latech.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.techmarket.R
import com.example.techmarket.latech.data.dataSource.home.HomeRepositoryImpl

@PreviewScreenSizes
@Composable
private fun SearchPreview() {
    SearchScreen(SearchViewModel(HomeRepositoryImpl()))
}


@Composable
fun SearchScreen(
     viewModel: SearchViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val searchState by viewModel.searchText.collectAsStateWithLifecycle()
        val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
        val products by viewModel.products.collectAsStateWithLifecycle()

        Text("Search", fontWeight = FontWeight.Bold, fontSize = 32.sp)

        Spacer(modifier = Modifier.height(12.dp))

        // Search bar
        SearchBar(viewModel,searchState)

        Spacer(modifier = Modifier.height(16.dp))

        ProductsList(products,isSearching)

    }
}

@Composable
fun SearchBar(viewModel: SearchViewModel,searchText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            placeholder = { Text("What are you looking for?") },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White,
                focusedContainerColor = Color(0xFFE0ECF8),
                unfocusedContainerColor = Color(0xFFE0ECF8),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(30.dp)),
        )
    }
}


@Composable
fun ProductsList(products: List<SearchProduct>,isSearching: Boolean) {
    if (isSearching){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }else {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            items(products) {
                ProductItem(product = it)
            }
        }
    }
}


@Composable
fun ProductItem(product: SearchProduct) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
    ){
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.product),
            contentDescription ="product"
        )
        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = product.name,fontWeight = FontWeight.Bold,fontSize =24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = product.price.toString(),fontSize = 18.sp)
        }
    }
}




