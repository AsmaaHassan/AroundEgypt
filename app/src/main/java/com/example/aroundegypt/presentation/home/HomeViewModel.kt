package com.example.github.presentation.repos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegypt.domain.model.Experience
import com.example.aroundegypt.presentation.home.RecentExpUiState
import com.example.github.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private val _showValidation = MutableStateFlow(false)
    val showValidation: StateFlow<Boolean> = _showValidation

    var recommendedState : RecommendedExpUiState by mutableStateOf(RecommendedExpUiState.Loading)
        private set

    var recentState : RecentExpUiState by mutableStateOf(RecentExpUiState.Loading)
        private set

    var searchState : RecentExpUiState by mutableStateOf(RecentExpUiState.Loading)
        private set

     var selectedExperience= mutableStateOf<Experience?>(null)
         private set

    fun selectExperience(item: Experience) {
        selectedExperience.value = item
    }
    init {
        observeSearch()
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        _showValidation.value = text.isNotEmpty() && text.length < 3

        if (text.isEmpty()) {
            _isSearchActive.value = false
        }
    }

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive: StateFlow<Boolean> = _isSearchActive

    fun onImeSearch() {
        if (_searchText.value.length < 3) {
            _showValidation.value = true
            return
        }
        _isSearchActive.value = true
    }

    fun resetSearch() {
        _isSearchActive.value = false
    }

    private fun observeSearch() {
        viewModelScope.launch {
            _searchText
                .debounce(500) // wait user to stop typing
                .distinctUntilChanged()
                .collect { query ->
                    if (query.length >= 3) {
                        _showValidation.value = false
                        searchApi(query)
                    }
                }
        }
    }

    private suspend fun searchApi(searchText: String) {
        viewModelScope.launch {
            searchState = RecentExpUiState.Loading
            runCatching { useCases.searchExperienceUseCase(searchText = searchText) }
                .onSuccess { list ->
                    searchState = RecentExpUiState.Success(list)
                }
                .onFailure { e ->
                    searchState = RecentExpUiState.Error
                }
        }
    }

      fun likeExperience(id: String) {
        viewModelScope.launch {
            searchState = RecentExpUiState.Loading
            runCatching { useCases.likeExperience(id) }
                .onSuccess { number ->
                    selectedExperience.value =
                        selectedExperience.value?.copy(likesNumber= number,isLiked= true)
                }
                .onFailure { e ->
                }
        }
    }



    fun loadRecommendedExp() {
        viewModelScope.launch {
            recommendedState = RecommendedExpUiState.Loading
            runCatching { useCases.getRecommendedExpUseCase() }
                .onSuccess { list ->
                    recommendedState = RecommendedExpUiState.Success(list)
                }
                .onFailure { e ->
                    recommendedState = RecommendedExpUiState.Error
                }
        }
    }

    fun loadRecentExp() {
        viewModelScope.launch {
            recentState = RecentExpUiState.Loading
            runCatching { useCases.getRecentExpUseCase() }
                .onSuccess { list ->

                    recentState = RecentExpUiState.Success(list)
                }
                .onFailure { e ->

                    recentState = RecentExpUiState.Error
                }
        }
    }

}
