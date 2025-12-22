package com.example.aroundegypt.presentation.home

import com.example.aroundegypt.domain.model.Experience
import com.example.github.presentation.repos.RecommendedExpUiState

/**
 * Created by AsmaaHassan on 17,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
sealed interface RecentExpUiState {
    data class Success(val data: List<Experience>) :RecentExpUiState
    object Loading : RecentExpUiState
    object Error : RecentExpUiState
}
