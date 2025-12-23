package com.example.github.presentation.repos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentation.compose_components.SectionTitle
import com.example.aroundegypt.presentation.compose_components.TopBar
import com.example.aroundegypt.presentation.compose_components.WelcomeSection
import com.example.aroundegypt.presentation.home.RecentExpUiState
import com.example.aroundegypt.presentation.home.compose_components.RecentExperienceItem
import com.example.aroundegypt.presentation.home.compose_components.RecommendedItem

/**
 * Home screen controller that manages the UI state and user interactions for the Around Egypt home page.
 * 
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 * 
 * @property viewModel The [HomeViewModel] handling the business logic and state.
 * @property navController The [NavHostController] for navigating between screens.
 */
class Home(val viewModel: HomeViewModel, val navController: NavHostController) : IExperienceActions {

    /**
     * Main Composable entry point for the Home screen.
     * Observes the search state and toggles between the default home content and search results.
     */
    @Composable
    fun HomeScreen() {
        val isSearchActive by viewModel.isSearchActive.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            TopBar(viewModel)
            if (isSearchActive) {
                SearchResultContent(viewModel.searchState)
            } else {
                HomeContent(viewModel)
            }
        }
    }

    /**
     * Displays a vertical list of experiences that match the search criteria.
     * 
     * @param searchState The UI state containing the list of found experiences.
     */
    @Composable
    fun SearchResultContent(searchState: RecentExpUiState) {
        LazyColumn() {
            when (searchState) {
                is RecentExpUiState.Success -> {
                    items(searchState.data) { experience ->
                        RecentExperienceItem(experience) {
                            onClickExperience(experience)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                else -> {}
            }
        }
    }


    /**
     * Displays the primary home content including:
     * - Welcome section
     * - Horizontal list of Recommended Experiences
     * - Vertical list of Most Recent Experiences
     * 
     * @param viewModel The view model used to trigger data loading and manage state.
     */
    @Composable
    fun HomeContent(viewModel: HomeViewModel) {
        val recentState = viewModel.recentState
        val recommendedState = viewModel.recommendedState

        LaunchedEffect(Unit) { viewModel.loadRecommendedExp() }
        LaunchedEffect(Unit) { viewModel.loadRecentExp() }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item { WelcomeSection() }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                SectionTitle("Recommended Experiences")
            }

            item {
                when (recommendedState) {
                    RecommendedExpUiState.Loading -> CircularProgressIndicator()
                    RecommendedExpUiState.Error -> Text("Something went wrong")
                    is RecommendedExpUiState.Success -> {
                        LazyRow {
                            items(recommendedState.data) { experience ->
                                RecommendedItem(experience) {
                                    onClickExperience(experience = experience)
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                            }
                        }
                    }
                }

            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                SectionTitle("Most Recent")
            }
            when (recentState) {
                is  RecentExpUiState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }

                is RecentExpUiState.Success -> {
                    items(recentState.data) { experience ->
                        RecentExperienceItem(experience) {
                            onClickExperience(experience = experience)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                else -> {}
            }
        }
    }

    /**
     * Navigates to the experience details screen when an experience is selected.
     * 
     * @param experience The [Experience] model that was clicked.
     */
    override fun onClickExperience(experience: Experience) {
        viewModel.selectExperience(experience)
        navController.navigate("experience_details")
    }

    /**
     * Placeholder implementation for liking an experience from the home screen.
     * 
     * @param experienceId The unique identifier of the experience to be liked.
     */
    override fun likeExperience(experienceId: String) {
        // TODO: Implement like action from home screen if needed
    }
}

/**
 * Defines the contract for user interactions with experience items across the application.
 */
interface IExperienceActions {
    /**
     * Callback triggered when an experience is clicked.
     */
    fun onClickExperience(experience: Experience)
    
    /**
     * Callback triggered when a like action is performed on an experience.
     */
    fun likeExperience(experienceId: String)
}
