package com.example.aroundegypt.domain.usecase

import com.example.github.domain.repository.ExperienceRepository

/**
 * Created by AsmaaHassan on 22,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

class LikeExperienceUseCase(private val repository: ExperienceRepository) {
    suspend operator fun invoke(experienceId: String) =
        repository.likeExperience(experienceId)
}