package com.example.aroundegypt.domain.usecase

import com.example.github.domain.repository.ExperienceRepository

/**
 * Created by AsmaaHassan on 16,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
class GetRecentExpUseCase(private val repository: ExperienceRepository) {
    suspend operator fun invoke() =
        repository.getRecentExperience()
}