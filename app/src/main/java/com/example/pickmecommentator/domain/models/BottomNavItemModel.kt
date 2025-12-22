package com.example.pickmecommentator.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemModel(
    val route: String,
    val icon: ImageVector,
    val title: String
)
