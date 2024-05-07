package com.sopt.now.compose.navigation.sample

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}