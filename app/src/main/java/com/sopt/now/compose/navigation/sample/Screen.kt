package com.sopt.now.compose.navigation.sample

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail")
}