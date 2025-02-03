package com.example.techmarket.latech.presentation.product



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.example.techmarket.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.ui.theme.onPrimaryContainerLight


@PreviewScreenSizes
@Composable
private fun ProductPreview() {
    ProductScreen(
        product = Product(
            id = 5,
            name = "Product Name",
            salary = 50.0,
            image = "https://bxvhnxfhejcwsmqehbor.supabase.co/storage/v1/object/sign/product/iPhone13.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJwcm9kdWN0L2lQaG9uZTEzLnBuZyIsImlhdCI6MTczNDU3MjA5OCwiZXhwIjoxNzY2MTA4MDk4fQ.1bxRorQfocFi8-vS6cxORJtXaZso9X2wqvsbcXLLBc0&t=2024-12-19T01%3A34%3A58.812Z",
            category = "Category",
            stock = true,
            colors = listOf("red", "black", "blue","Gray"),
            capacity = listOf("32,64")
        ),

       state = ProductState(),
        onAction = {},
    )
}


@Composable
fun ProductScreen(
    product: Product,
    state: ProductState,
    onAction:(ProductAction)->Unit,
) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )



         Text(
                text = if(product.stock==true) { "New" } else { "Out of Stock"},
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier
                    .padding(start = 18.dp)
                    .background(Color.LightGray)
                    .padding(5.dp)
                    .align(Alignment.Start)
          )



        // Product Image
        Image(
            painter = painterResource(id = R.drawable.product),
            contentDescription = product.name,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )



        ColorSection(
            modifier = Modifier.align(Alignment.Start),
            product = product
        )


        CapacitySelection(
            modifier = Modifier.align(Alignment.Start),
            product= product
        )


        // Add to Cart Button
        Button(
            onClick = {
                onAction(ProductAction.AddToCart(product))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(50.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonColors(
                containerColor = onPrimaryContainerLight,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            )
        ) {
            Text("Add to Cart")
        }

    }
}

@Composable
fun CapacitySelection(product: Product, modifier: Modifier ) {

    var selectedCapacity by remember { mutableStateOf(product.capacity) }

    // Capacity Selection
    Text(
        "Capacity",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .padding(start = 18.dp)

    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        product.capacity?.forEach { capacity ->
            Text(
                text = capacity,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedCapacity = product.capacity },
                color = if (product.capacity == selectedCapacity) Color.Blue else Color.Black
            )
        }

    }
}

@Composable
fun ColorSection(product: Product, modifier: Modifier ) {

    var selectedColor by remember { mutableStateOf(product.colors?.first()) }
    // Color Selection
    Text(
        "Color",
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .padding(start = 18.dp)

    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        product.colors?.forEach { color ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp)
                    .background(
                        Color(android.graphics.Color.parseColor(color)),
                        shape = CircleShape
                    )
                    .selectable(
                        selected = color == selectedColor,
                        onClick = { selectedColor = color }
                    )
            )
        }
    }
}
