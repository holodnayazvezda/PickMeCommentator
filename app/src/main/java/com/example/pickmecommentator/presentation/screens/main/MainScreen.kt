package com.example.pickmecommentator.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.pickmecommentator.presentation.components.BottomNavigationBar
import com.example.pickmecommentator.presentation.navigation.bottom.BottomNavigationHost

@Composable
fun MainScreen(
    bottomNavController: NavHostController
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        BottomNavigationHost(
            modifier = Modifier.padding(innerPadding),
            bottomNavController = bottomNavController
        )
    }
}