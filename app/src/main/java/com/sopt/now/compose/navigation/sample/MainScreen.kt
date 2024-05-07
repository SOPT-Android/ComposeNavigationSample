package com.sopt.now.compose.navigation.sample

interface Screen {
    val route: String

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

sealed class MainScreen(override val route: String) : Screen {
    data object Home : MainScreen("home")
    data object Detail : MainScreen("detail")
    data object PlayGround : MainScreen("playground")
}

sealed class AuthScreen(override val route: String):Screen {
    data object Auth : AuthScreen("auth")
    data object Login : AuthScreen("login")
}