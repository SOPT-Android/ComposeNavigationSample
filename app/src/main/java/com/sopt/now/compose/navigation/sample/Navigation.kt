package com.sopt.now.compose.navigation.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onSubmitUserName = { userName ->
                    navController.navigate(Screen.Detail.withArgs(userName))
                }
            )
        }
        composable(
            route = Screen.Detail.route + "/{userName}",
            arguments = listOf(
                navArgument("userName") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { entry ->
            val userName = entry.arguments?.getString("userName")
            DetailScreen(
                navController = navController,
                name = userName.orEmpty()
            )
        }
    }
}


@Composable
fun HomeScreen(
    onSubmitUserName: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var userName by remember { mutableStateOf("") }
        Text(text = "Input User Name")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = userName,
            onValueChange = {
                userName = it
            })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onSubmitUserName(userName) }) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun DetailScreen(
    navController: NavHostController,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "DETAIL")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "User Name: $name")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navController.navigate("home")
        }) {
            Text(text = "Go to Home")
        }
    }
}