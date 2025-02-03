package com.example.techmarket.latech.presentation.admin.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.techmarket.latech.presentation.admin.AdminViewModel

@Composable
fun OrderManagementScreen(viewModel: AdminViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Orders")
        viewModel.state.orders.forEachIndexed { index, order ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text("Order by ${order.userName}")
                Text("Details: ${order.details}")

//                Row {
//                    Button(onClick = { state.acceptOrder(index) }) {
//                        Text("Accept")
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(onClick = { viewModel.refuseOrder(index) }) {
//                        Text("Refuse")
//                    }
//                }
            }
        }
    }
}