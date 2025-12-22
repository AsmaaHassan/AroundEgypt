package com.example.github.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aroundegypt.AppDestinations
import com.example.github.presentation.repodetails.ExperienceDetails
import com.example.github.presentation.repos.Home
import com.example.github.presentation.repos.HomeViewModel

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */


@Composable
fun AppNavHost(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME.route,
        modifier = Modifier.fillMaxSize()
    ) {

        composable("home") { backStackEntry ->

            Home(viewModel = homeViewModel,navController).HomeScreen()
        }


        composable(AppDestinations.FAVORITES.route) {
            //FavoritesScreen()
        }

        composable(AppDestinations.PROFILE.route) {
            //  ProfileScreen()
        }
        composable("experience_details") { backStackEntry ->
            // use the SAME parent entry
//                val parentEntry = remember(backStackEntry) {
//                    navController.getBackStackEntry("home_graph")
//                }
//                val homeViewModel: HomeViewModel = hiltViewModel()

            ExperienceDetails(
                viewModel = homeViewModel
            )
        }

    }
}
