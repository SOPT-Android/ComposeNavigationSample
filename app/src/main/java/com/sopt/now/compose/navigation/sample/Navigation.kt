package com.sopt.now.compose.navigation.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainScreen.Home.route
    ) {
        navigation(
            startDestination = AuthScreen.Login.route,
            route = AuthScreen.Auth.route
        ) {
            composable(AuthScreen.Login.route) {
                LoginScreen(
                    loginAction = {
                        navController.navigate(MainScreen.Home.route) {
                            popUpTo(AuthScreen.Auth.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        composable(MainScreen.Home.route) {
            HomeScreen(
                navigateToDetail = { navController.navigate(MainScreen.Detail.route) },
                navigateToPlayGround = { navController.navigate(MainScreen.PlayGround.route) }
            )
        }
        composable(MainScreen.Detail.route) {
            DetailScreen(
                navigateToHome = { navController.navigate(MainScreen.Home.route) },
                navigateToPlayGround = { navController.navigate(MainScreen.PlayGround.route) }
            )
        }
        composable(MainScreen.PlayGround.route) {
            PlayGroundScreen(
                navigateToHome = {
                    navController.navigate(MainScreen.Home.route) {
                        popUpTo(MainScreen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToDetail = { navController.navigate(MainScreen.Detail.route) }
            )
        }
    }
}

@Composable
fun LoginScreen(
    loginAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Home")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = loginAction) {
            Text(text = "Login")
        }
    }
}

@Composable
fun HomeScreen(
    navigateToDetail: () -> Unit,
    navigateToPlayGround: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Home")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToDetail) {
            Text(text = "Go to Detail")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToPlayGround) {
            Text(text = "Go to PlayGround")
        }
    }
}

@Composable
fun DetailScreen(
    navigateToHome: () -> Unit,
    navigateToPlayGround: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "DETAIL")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToHome) {
            Text(text = "Go to Home")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToPlayGround) {
            Text(text = "Go to PlayGround")
        }
    }
}

@Composable
fun PlayGroundScreen(
    navigateToHome: () -> Unit,
    navigateToDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "PlayGround")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToHome) {
            Text(text = "Go to Home")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = navigateToDetail) {
            Text(text = "Go to Detail")
        }
    }
}
