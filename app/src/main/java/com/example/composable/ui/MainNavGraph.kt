package com.example.composable.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val DETAIL_ROUTE = "home/feature/detail"
}

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
//        navigation(
//            route = MainDestinations.HOME_ROUTE,
//            startDestination = HomeSections.Feature.route
//        ){
//            addHomeGraph()
//        }
//        composable(MainDestinations.DETAIL_ROUTE) {
//            HomeDetail()
//        }
    }
}