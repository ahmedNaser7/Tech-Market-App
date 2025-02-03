package com.example.techmarket.latech.presentation.admin



import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techmarket.core.navigation.Login
import com.example.techmarket.latech.presentation.admin.components.OrderManagementScreen
import com.example.techmarket.latech.presentation.admin.components.ProductManagementScreen


@PreviewScreenSizes
@Composable
private fun PreviewAdminProductScreen() {

}


// Admin Dashboard Screen
@Composable
fun AdminDashboardScreen(navController: NavController, viewModel: AdminViewModel) {

    var currentTab by remember { mutableStateOf(AdminTab.Products) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TabRow(selectedTabIndex = currentTab.ordinal) {
            AdminTab.entries.forEach { tab ->
                Tab(
                    selected = currentTab == tab,
                    onClick = { currentTab = tab },
                    text = { Text(tab.title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        IconButton(
            onClick = {
                viewModel.signOutAdmin()
                Log.d("signOut", "AdminDashboardScreen: ${viewModel.state}")
                navController.navigate(Login) {
                    popUpTo(0)
                }
            }, modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Sign Out"
            )
        }


        when (currentTab) {
            AdminTab.Products -> ProductManagementScreen(viewModel)
            AdminTab.Orders -> OrderManagementScreen(viewModel)
        }

    }
}


// Data Models
data class Order(val userName: String, val details: String)

// Enum for Tabs
enum class AdminTab(val title: String) {
    Products("Products"),
    Orders("Orders")
}
