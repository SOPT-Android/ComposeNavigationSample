package com.sopt.now.compose.navigation.sample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

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

sealed class MainScreen(
    override val route: String,
) : Screen {
    data object Main : MainScreen("main")
    data object Home : MainScreen("home")
    data object MyPage : MainScreen("mypage")
    data object PlayGround : MainScreen("playground")

    data class BottomNavigationItem(
        val screen: MainScreen,
        val icon: ImageVector,
        val label: String
    )

    fun toNavigationItem(): BottomNavigationItem {
        return when (this) {
            Home -> BottomNavigationItem(Home, Icons.Filled.Home, "Home")
            MyPage -> BottomNavigationItem(MyPage, Icons.Filled.Info, "MyPage")
            PlayGround -> BottomNavigationItem(PlayGround, Icons.Filled.Place, "PlayGround")
            else -> BottomNavigationItem(Home, Icons.Filled.Home, "Home")
        }
    }

    companion object {
        private val entries: List<MainScreen> = listOf(Home, MyPage, PlayGround)

        operator fun contains(route: String?): Boolean {
            return entries.any { it.route == route }
        }
    }
}

sealed class AuthScreen(override val route: String) : Screen {
    data object Auth : AuthScreen("auth")
    data object Login : AuthScreen("login")
    data object SignUp : AuthScreen("signup")
}

sealed class DetailScreen(override val route: String) : Screen {
    data object Detail : DetailScreen("detail")
}