package com.example.techmarket.latech.presentation.admin.components

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.techmarket.core.presentation.util.uriToByteArrayAndMimeType
import com.example.techmarket.latech.presentation.admin.AdminViewModel


@Composable
fun ProductManagementScreen(viewModel: AdminViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var productId = 0
        var productName by remember { mutableStateOf(TextFieldValue()) }
        var productPrice by remember { mutableStateOf(TextFieldValue()) }

        Text("Add Product")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = productName,
                onValueChange = { productName = it },
                modifier = Modifier.sizeIn(maxWidth = 200.dp),
                placeholder = { Text("Product Name") },

                )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                modifier = Modifier.sizeIn(maxWidth = 200.dp),
                placeholder = { Text("Product Price") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Todo(add image of product)
            ButtonProductImage(viewModel, LocalContext.current.applicationContext)

            // Todo(add colour of product)
            Button(
                onClick = {
                    viewModel.addProductAdmin(
                        productId,
                        productName.text,
                        productPrice.text.toDoubleOrNull() ?: 0.0
                    )
                    productId += 1
                }, shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Product List")
        viewModel.state.products.forEachIndexed { index, product ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("${product.name} - \$${product.salary}")
                Row {
                    Button(onClick = {
                        viewModel.editProductAdmin(
                            index,
                            product.copy(name = "Updated ${product.name}")
                        )
                    }) {
                        Text("Edit")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { viewModel.deleteProduct(2) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonProductImage(viewModel: AdminViewModel, context: Context) {
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            val (byteArray, mimeType) = context.uriToByteArrayAndMimeType(uri)
            if (byteArray != null && mimeType != null) {
                viewModel.uploadImage(byteArray, mimeType)
            } else {
                Log.d("PhotoPicker", "Failed to convert URI to ByteArray or determine MIME type")
            }
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    Button(onClick = {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }) {
        Text("Add image")
    }
}