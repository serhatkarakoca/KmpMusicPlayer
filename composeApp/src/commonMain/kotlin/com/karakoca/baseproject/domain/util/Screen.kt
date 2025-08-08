package com.karakoca.baseproject.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kmpbaseproject.composeapp.generated.resources.Res
import kmpbaseproject.composeapp.generated.resources.favorites
import kmpbaseproject.composeapp.generated.resources.home
import org.jetbrains.compose.resources.StringResource

interface Destinations {
    val title: StringResource
    val route: String
    val routeWithArgs: String
    val icon: ImageVector?
}

sealed class Screen : Destinations {
    data object HomeScreen : Screen() {
        override val title: StringResource
            get() = Res.string.home
        override val route: String
            get() = "home"
        override val routeWithArgs: String
            get() = route
        override val icon: ImageVector
            get() = Icons.Default.Home
    }

    data object SplashScreen : Screen() {
        override val title: StringResource
            get() = Res.string.home
        override val route: String
            get() = "splash"
        override val routeWithArgs: String
            get() = route
        override val icon: ImageVector?
            get() = null
    }

    data object FavoritesScreen : Screen() {
        override val title: StringResource
            get() = Res.string.favorites
        override val route: String
            get() = "favorites"
        override val routeWithArgs: String
            get() = route
        override val icon: ImageVector?
            get() = Icons.Default.Favorite
    }
}

val screens = listOf(Screen.HomeScreen, Screen.SplashScreen, Screen.FavoritesScreen)
val topBarNotVisible = listOf<Screen>(Screen.SplashScreen)
val bottomNavigations = listOf<Screen>(Screen.HomeScreen, Screen.FavoritesScreen)