package com.example.github.presentation.repos

import com.example.aroundegypt.domain.model.Experience

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */


sealed interface RecommendedExpUiState {
    data class Success(val data: List<Experience>) : RecommendedExpUiState
    object Loading : RecommendedExpUiState
    object Error : RecommendedExpUiState
}
