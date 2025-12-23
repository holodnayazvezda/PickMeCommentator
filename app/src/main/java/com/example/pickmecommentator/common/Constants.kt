package com.example.pickmecommentator.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Menu
import com.example.pickmecommentator.domain.models.BottomNavItemModel
import com.example.pickmecommentator.presentation.navigation.bottom.BottomNavRoutes

object Constants {
    val bottomNavItems = listOf(
        BottomNavItemModel(
            route = BottomNavRoutes.CurrentSessionScreen.route,
            icon = Icons.Outlined.Home,
            title = "Трансляция"
        ),
        BottomNavItemModel(
            route = BottomNavRoutes.ListOfSessionsScreen.route,
            icon = Icons.Rounded.Menu,
            title = "Истрия"
        )
    )

    const val SOCKET_SERVER_URL = "http://10.90.96.32:8080/"
    const val TRIPS_API_SERVER_URL = "http://10.90.96.32:8001/"
}