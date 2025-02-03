package com.example.techmarket.latech.presentation.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.techmarket.R
import com.example.techmarket.ui.theme.onPrimaryContainerLight


@PreviewScreenSizes
@Composable
private fun ProfilePreview() {
    ProfileScreen(NavController(LocalContext.current),ProfileState()){}
}

@Composable
fun ProfileScreen(
    navController: NavController,
    state: ProfileState,
    onLogOut:(action:ProfileAction)->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // Header (title , image and name)
        ProfileHeader(state)

        // Menu Items
        ProfileMenu(navController,onLogOut)

    }
}

@Composable
fun ProfileMenu(navController: NavController, onLogOut: (action: ProfileAction) -> Unit) {
    // Menu Items
    val menuItems = listOf(
        "Orders",
        "Returns and refunds",
        "Account information",
        "Security and settings",
        "Log out"
    )

    menuItems.forEach { item ->
        Text(
            text = item,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clickable {
                    when (item) {
                        "Account information" -> {
                            navController.navigate("info-profile")
                        }

                        "Log out" -> {
                            onLogOut(ProfileAction.OnLogout)
                        }
                    }
                }
                .shadow(11.dp, ambientColor = Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)

        )
    }
}

@Composable
fun ProfileHeader(state: ProfileState) {

    // Header
    Text(
        text = "Account",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 24.dp)
    )


    // Profile Picture and Name
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.picture), // Replace with your image resource
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = state.profile?.userName!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Premium member",
                fontSize = 12.sp,
                color = onPrimaryContainerLight
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(NavController(LocalContext.current),ProfileState()){}
}