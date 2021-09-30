package com.example.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import com.example.composable.ui.pages.*
import com.example.composable.ui.theme.ComposableTheme
import com.example.composable.viewModel.MainViewModel
import kotlinx.coroutines.launch

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    Hospital(R.string.nav_hospital, Icons.Filled.LocalHospital, "hospital"),
    Doctor(R.string.nav_doctor, Icons.Filled.Masks, "doctor"),
    Deal(R.string.nav_deal, Icons.Filled.Work, "deal"),
    Search(R.string.nav_search, Icons.Filled.Search, "search"),
    Profile(R.string.nav_profile, Icons.Filled.Person, "profile")
}

@Composable
fun MainContent() {
    ComposableTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(scaffoldState) },
            drawerContent = { Drawer() }
        ) {
            Box(modifier = Modifier.padding(it)) {
                BottomNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun TopBar(scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Button", modifier = Modifier
                .padding(horizontal = 10.dp)
                .clickable {
                    scope.launch {
                        if (scaffoldState.drawerState.isClosed) {
                            scaffoldState.drawerState.open()
                        } else scaffoldState.drawerState.close()
                    }
                })
        },
        backgroundColor = Color.Transparent,
        contentColor = Color.Gray,
        elevation = 1.dp
    )
}

@Composable
fun Drawer() {

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
        NavHost(navController = navController, startDestination = HomeSections.Hospital.route) {
            composable(HomeSections.Hospital.route) { Feature(MainViewModel()) }
            composable(HomeSections.Doctor.route) { Doctors() }
            composable(HomeSections.Deal.route) { Deals() }
            composable(HomeSections.Search.route) { Search() }
            composable(HomeSections.Profile.route) { Setting() }
        }
    }
}

