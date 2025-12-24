package com.example.pickmecommentator.presentation.screens.main

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import com.example.pickmecommentator.common.Constants
import com.example.pickmecommentator.presentation.navigation.bottom.BottomNavRoutes
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentTripMessagesScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions.ListOfSessionsScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState { Constants.bottomNavItems.size }
    val coroutineScope = rememberCoroutineScope()
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedItemIndex = pagerState.currentPage
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = MaterialTheme.colorScheme.background // Set background to main dark color
            ) {
                Constants.bottomNavItems.forEachIndexed { index, destination ->
                    val selected = selectedItemIndex == index
                    val scale = remember { Animatable(1f) }

                    LaunchedEffect(selected) {
                        if (selected) {
                            scale.animateTo(
                                targetValue = 1.2f,
                                animationSpec = tween(durationMillis = 100)
                            )
                            delay(100)
                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(durationMillis = 100)
                            )
                        }
                    }

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            selectedItemIndex = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.title,
                                modifier = Modifier.scale(scale.value)
                            )
                        },
                        label = { Text(destination.title) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            userScrollEnabled = true
        ) { page ->
            when (Constants.bottomNavItems[page].route) {
                BottomNavRoutes.CurrentSessionScreen.route -> CurrentTripMessagesScreen()
                BottomNavRoutes.ListOfSessionsScreen.route -> ListOfSessionsScreen()
            }
        }
    }
}
