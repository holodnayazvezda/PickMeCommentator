package com.example.pickmecommentator.presentation.screens.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavHostController
import com.example.pickmecommentator.common.Constants
import com.example.pickmecommentator.presentation.navigation.bottom.BottomNavRoutes
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentSessionScreen
import com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions.ListOfSessionsScreen
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
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Constants.bottomNavItems.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                destination.icon,
                                contentDescription = destination.title
                            )
                        },
                        label = { Text(destination.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            userScrollEnabled = true
        ) { page ->
            when (Constants.bottomNavItems[page].route) {
                BottomNavRoutes.CurrentSessionScreen.route -> CurrentSessionScreen()
                BottomNavRoutes.ListOfSessionsScreen.route -> ListOfSessionsScreen()
            }
        }
    }
}