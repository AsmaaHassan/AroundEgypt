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
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

class Home(val viewModel: HomeViewModel, val navController: NavHostController) : IExperienceActions {

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
                        //  RecommendedList(recommendedState.data)
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

    override fun onClickExperience(experience: Experience) {
        viewModel.selectExperience(experience)   // store object
        navController.navigate("experience_details")
    }

    override fun likeExperience(experienceId: String) {
        TODO("Not yet implemented")
    }
}

interface IExperienceActions {
    fun onClickExperience(experience: Experience)
    fun likeExperience(experienceId: String)
}