package com.example.github.domain.usecase

import com.example.github.domain.repository.ExperienceRepository

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
class GetRecommendedExpUseCase(private val repository: ExperienceRepository) {
    suspend operator fun invoke() =
        repository.getRecommendedExperience()
}