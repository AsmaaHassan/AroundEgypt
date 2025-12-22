package com.example.github.domain.repository

import com.example.aroundegypt.domain.model.Experience

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
interface ExperienceRepository {
    suspend fun getRecommendedExperience(): List<Experience>
    suspend fun getRecentExperience(): List<Experience>
    suspend fun searchExperience(searchText: String): List<Experience>

    suspend fun likeExperience(experienceId: String): Int
}