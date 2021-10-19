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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composable.ui.pages.*
import com.example.composable.ui.theme.ComposableTheme
import com.example.composable.viewModel.AutoCompleteViewModel
import com.example.composable.viewModel.DealViewModel
import com.example.composable.viewModel.DoctorViewModel
import com.example.composable.viewModel.HospitalViewModel
import kotlinx.coroutines.launch

sealed class BottomNavItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
){
    object Hospital : BottomNavItem(R.string.nav_hospital, Icons.Filled.LocalHospital, "hospital")
    object Doctor : BottomNavItem(R.string.nav_doctor, Icons.Filled.Masks, "doctor")
    object Deal : BottomNavItem(R.string.nav_deal, Icons.Filled.Work, "deal")
    object Search : BottomNavItem(R.string.nav_search, Icons.Filled.Search, "search")
    object Profile : BottomNavItem(R.string.nav_profile, Icons.Filled.Person, "profile")
}

@Composable
fun HomeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Hospital.route) {
        composable(BottomNavItem.Hospital.route) { Hospitals({}, HospitalViewModel()) }
        composable(BottomNavItem.Doctor.route) { Doctors({}, DoctorViewModel()) }
        composable(BottomNavItem.Deal.route) { Deals({}, DealViewModel()) }
        composable(BottomNavItem.Search.route) { Search(viewModel = AutoCompleteViewModel()) }
        composable(BottomNavItem.Profile.route) { Setting() }
    }
}

@Composable
fun MainContent() {
    ComposableTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBar(scaffoldState) },
            drawerContent = { Drawer() },
            bottomBar = { BottomNavigation(navController) }
        ) {
            Box(modifier = Modifier.padding(it)) {
                HomeNavigation(navController)
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
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Hospital,
        BottomNavItem.Doctor,
        BottomNavItem.Deal,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.design_default_color_primary),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = stringResource(item.title)) },
                label = { Text(text = stringResource(item.title)) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
