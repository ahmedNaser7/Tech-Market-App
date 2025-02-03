package com.example.techmarket

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.techmarket.core.data.local.AppPreferencesDataSource
import com.example.techmarket.core.navigation.BottomNavigationItemsList
import com.example.techmarket.core.navigation.TechMarketBottomBar
import com.example.techmarket.core.navigation.TechMarketNavigationBar
import com.example.techmarket.latech.domain.dataSource.auth.AuthRepository
import com.example.techmarket.latech.presentation.onBoarding.components.viewModel.OnBoardingViewModel
import com.example.techmarket.ui.theme.TechMarketTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // check for data of background
        // old Splash Screen
        setTheme(R.style.Theme_SplashScreen)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // to make a flows sync process
        // splash screen 12v or above
        installSplashScreen()
        setContent {
            TechMarketApp()
        }
    }
}

