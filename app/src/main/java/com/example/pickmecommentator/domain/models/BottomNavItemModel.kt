package com.example.pickmecommentator.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItemModel(
    val route: String,
    @param:DrawableRes val icon: ImageVector,
    val title: String
)
