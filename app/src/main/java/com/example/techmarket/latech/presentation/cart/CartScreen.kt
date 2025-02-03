import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techmarket.R
import com.example.techmarket.latech.domain.model.Product
import com.example.techmarket.latech.presentation.cart.CartState
import com.example.techmarket.ui.theme.onPrimaryContainerLight


@PreviewScreenSizes
@Composable
private fun CartPreview() {
    CartScreen(CartState())
}



@Composable
fun CartScreen(
    state: CartState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Checkout",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Cart items list
        LazyColumn(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items(state.products) {
                CartItemView(it)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Delivery Address
        CheckoutDetailRow(
            label = "Delivery",
            value = state.address
        )

        // Payment Method
        CheckoutDetailRow(
            label = "Payment",
            value = "Cash"
        )

        // Total Amount
        CheckoutDetailRow(
            label = "Total",
            value = "EG ${state.cartDetails?.totalPrice}",
            valueColor = Color.Black
        )

//        // Discount Code
//        Text(
//            text = "Enter a discount code",
//            fontSize = 14.sp,
//            color = onPrimaryContainerLight,
//            textDecoration = TextDecoration.Underline,
//            modifier = Modifier
//                .padding(vertical = 8.dp)
//                .clickable { onEnterDiscountClick() }
//        )

        // Pay Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = onPrimaryContainerLight),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Pay",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun CartItemView(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .shadow(
                11.dp,
                ambientColor = Color.Gray,
                spotColor = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.product),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "USD ${product.salary}", fontSize = 14.sp, color = Color.Gray)
            Text(text = product.brand?:"", fontSize = 14.sp, color = Color.Gray)
        }
        Text(text = "x${1}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CheckoutDetailRow(label: String, value: String, valueColor: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 18.sp, color = Color.Black,fontWeight = FontWeight.Bold)
        Text(text = value, fontSize = 18.sp, color = onPrimaryContainerLight, fontWeight = FontWeight.Bold)
    }
}

//data class CartItemUi(
//    val name: String,
//    val price: Double,
//    val color: String,
//    val quantity: Int,
//    val imageRes: Int
//)
//
//data class CheckoutState(
//    val items: List<CartItemUi>,
//    val deliveryAddress: String,
//    val paymentMethod: String,
//    val totalAmount: Double
//)
