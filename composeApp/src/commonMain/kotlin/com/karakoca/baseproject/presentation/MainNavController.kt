package com.karakoca.baseproject.presentation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.karakoca.baseproject.domain.util.Screen
import com.karakoca.baseproject.domain.util.bottomNavigations
import com.karakoca.baseproject.domain.util.screens
import com.karakoca.baseproject.domain.util.topBarNotVisible
import com.karakoca.baseproject.presentation.favorite.FavoriteScreen
import com.karakoca.baseproject.presentation.home.HomeScreen
import com.karakoca.baseproject.ui.AppFonts
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavController() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination =
        screens.find { it.routeWithArgs == currentBackStack?.destination?.route }
            ?: Screen.HomeScreen
    val topBarTitle = currentDestination.title
    val topBarVisible = currentDestination !in topBarNotVisible
    var selectedBottomBar by remember { mutableStateOf<String>(Screen.HomeScreen.route) }

    LaunchedEffect(key1 = currentDestination) {
        selectedBottomBar = bottomNavigations.find { it.route == currentDestination.route }?.route
            ?: Screen.HomeScreen.route
    }

    Scaffold(topBar = {
        if (topBarVisible)
            TopAppBar(title = {
                Text(
                    text = stringResource(topBarTitle),
                    fontFamily = AppFonts(),
                    fontWeight = FontWeight.SemiBold
                )
            }, navigationIcon = {
                AnimatedVisibility(currentDestination.route != Screen.HomeScreen.route) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            })
    }, bottomBar = {
        NavigationBar(

            contentColor = Color.LightGray,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            bottomNavigations.forEachIndexed { _, destination ->
                NavigationBarItem(
                    icon = {
                        destination.icon?.let { it1 ->
                            Icon(
                                imageVector = it1,
                                contentDescription = null
                            )
                        }
                    },
                    alwaysShowLabel = false,
                    label = {
                        Text(stringResource(resource = destination.title))
                    },
                    selected = selectedBottomBar == destination.route,
                    onClick = {
                        if (currentDestination.route != destination.route) {
                            val popBackStack = navController.popBackStack(destination.route, false)
                            if (!popBackStack)
                                navController.navigate(destination.route) {
                                    popUpTo(destination.route) {
                                        inclusive = true
                                        saveState = true
                                    }
                                }
                        }
                    }
                )
            }
        }
    }) {
        NavHost(
            modifier = Modifier.fillMaxSize().padding(it),
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }

            composable(Screen.FavoritesScreen.route) {
                FavoriteScreen()
            }
        }
    }
}