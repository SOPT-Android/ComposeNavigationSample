package com.sopt.now.compose.navigation.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.navigation.sample.ui.theme.ComposeNavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationSampleTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItemIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val currentDestination: String? =
                        navController.currentBackStackEntryAsState().value?.destination?.route

                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(
                                visible = currentDestination in MainScreen,
                                enter = fadeIn() + slideIn { IntOffset(0, it.height) },
                                exit = fadeOut() + slideOut { IntOffset(0, it.height) }
                            ) {
                                NavigationBar {
                                    listOf(
                                        MainScreen.Home,
                                        MainScreen.MyPage,
                                        MainScreen.PlayGround
                                    ).map(MainScreen::toNavigationItem)
                                        .forEachIndexed { index, item ->
                                            NavigationBarItem(
                                                selected = selectedItemIndex == index,
                                                onClick = {
                                                    selectedItemIndex = index
                                                    navController.navigate(item.screen.route)
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = item.icon,
                                                        contentDescription = item.label
                                                    )
                                                },
                                                label = { Text(text = item.label) }
                                            )
                                        }
                                }
                            }
                        }
                    ) { innerPadding ->
                        Navigation(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeNavigationSampleTheme {
        Greeting("Android")
    }
}