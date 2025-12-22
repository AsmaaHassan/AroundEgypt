package com.example.aroundegypt

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.ui.theme.AroundEgyptTheme
import com.example.github.presentation.navigation.AppNavHost
import com.example.github.presentation.repos.HomeViewModel
import com.example.github.presentation.repos.IExperienceActions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AroundEgyptTheme {
                AroundEgyptApp(this,homeViewModel)
            }
        }
    }


}


@Composable
fun AroundEgyptApp(context: Context, homeViewModel: HomeViewModel) {

    val navController = rememberNavController()
    var currentDestination by rememberSaveable {
        mutableStateOf(AppDestinations.HOME)
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(destination.icon, contentDescription = destination.label)
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = {
                        currentDestination = destination
                        navController.navigate(destination.route) {
                            popUpTo(AppDestinations.HOME.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        AppNavHost(navController = navController, homeViewModel = homeViewModel)

    }

}

enum class AppDestinations(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    HOME("home", "Home", Icons.Default.Home),
    FAVORITES("favorites", "Favorites", Icons.Default.Favorite),
    PROFILE("profile", "Profile", Icons.Default.AccountBox)
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AroundEgyptTheme {
    }
}