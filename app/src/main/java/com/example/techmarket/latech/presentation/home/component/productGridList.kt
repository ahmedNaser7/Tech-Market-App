package com.example.techmarket.latech.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.techmarket.R
import com.example.techmarket.latech.domain.model.Product

@PreviewScreenSizes
@Composable
private fun ProductListPreview() {
    ProductsGridList(
        productList = listOf(
            Product(1, "Product 1", 10.0),
            Product(2, "Product 2", 15.0),
            Product(3, "Product 3", 20.0),
        ),
        onClick = {}
    )
}

@Composable
fun ProductsGridList(
    productList: List<Product>,
    onClick: (Product) -> Unit,
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(productList) { product ->
            val productItem = Product(
                id = product.id,
                name = product.name,
                salary = product.salary,
                brand = product.brand,
                colors = product.colors,
                stock = product.stock,
                category = product.category,
                capacity = product.capacity,
                imageRes = product.imageRes,
            )
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .shadow(1.dp, ambientColor = Color.White, spotColor = Color.LightGray)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(8.dp)
                    .clickable {
                        onClick(productItem)
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.product),
                        contentDescription = "",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = product.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${product.salary}",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
