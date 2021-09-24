package com.example.composable

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composable.ui.pages.Booking
import com.example.composable.ui.pages.Feature
import com.example.composable.ui.pages.Search
import com.example.composable.ui.pages.Setting
import com.example.composable.ui.theme.ComposableTheme

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    Feature(R.string.nav_home, Icons.Outlined.Home, "home/feature"),
    Search(R.string.nav_search, Icons.Outlined.Search, "home/search"),
    Booking(R.string.nav_bookng, Icons.Outlined.List, "home/booking"),
    Setting(R.string.nav_setting, Icons.Outlined.Settings, "home/setting")
}

@Composable
fun MainContent() {
    ComposableTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigation(navController) }
        ) {
//            MainNavGraph(navController = navController)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Button")
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.Gray,
        elevation = 1.dp
    )
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    Scaffold(bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            HomeSections.values().forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, contentDescription = screen.name) },
                    label = { Text(stringResource(id = screen.title)) },
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) {
        NavHost(navController = navController, startDestination = HomeSections.Feature.route) {
            composable(HomeSections.Feature.route) { Feature() }
            composable(HomeSections.Search.route) { Search() }
            composable(HomeSections.Booking.route) { Booking() }
            composable(HomeSections.Setting.route) { Setting() }
        }
    }
}

