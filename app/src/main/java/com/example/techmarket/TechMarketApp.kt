package com.example.techmarket

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.techmarket.core.navigation.TechMarketBottomBar
import com.example.techmarket.core.navigation.TechMarketNavigationBar
import com.example.techmarket.ui.theme.TechMarketTheme

@Composable
fun TechMarketApp() {
    TechMarketTheme {
        val navController = rememberNavController()
        var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                TechMarketBottomBar(
                    navController,
                    selectedItemIndex,
                    onItemSelected = {
                        selectedItemIndex = it
                    }
                )
            }
        ) { innerPadding ->

            TechMarketNavigationBar(
                navController,
                modifier = Modifier.padding(innerPadding),
            )
        }

    }
}