package com.example.aroundegypt.domain.usecase

import com.example.github.domain.repository.ExperienceRepository

/**
 * Created by AsmaaHassan on 22,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
class SearchExperienceUseCase(private val repository: ExperienceRepository) {
    suspend operator fun invoke(searchText: String) =
        repository.searchExperience(searchText)
}