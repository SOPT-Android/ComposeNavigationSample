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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlin.reflect.typeOf

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(onNavigateToProfile = { id ->
                navController.navigate(Screen.Profile(id))
            })
        }
        composable<Screen.Profile> { backStackEntry ->
            val profile: Screen.Profile = backStackEntry.toRoute()
            ProfileScreen(
                profile, moveToSearch = {
                    navController.navigate(
                        Screen.Search(
                            Screen.SearchParameters(
                                "query",
                                emptyList()
                            )
                        )
                    )
                }
            )
        }

        composable<Screen.Search>(
            typeMap = mapOf(typeOf<Screen.SearchParameters>() to searchParametersType)
        ) { backStackEntry ->
            val searchParameters = backStackEntry.toRoute<Screen.Search>().parameters
            SearchScreen(searchParameters)
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateToProfile: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var id by remember { mutableStateOf("") }
        Text(text = "HOME")
        TextField(
            value = id,
            onValueChange = { id = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onNavigateToProfile(id) }) {
            Text(text = "Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(
    profile: Screen.Profile,
    moveToSearch: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Profile")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "id = ${profile.id}")
        Button(onClick = { moveToSearch() }) {
            Text(text = "Go to Search")
        }
    }
}

@Composable
fun SearchScreen(searchParameters: Screen.SearchParameters) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Search")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "query = ${searchParameters.searchQuery}")
    }
}
