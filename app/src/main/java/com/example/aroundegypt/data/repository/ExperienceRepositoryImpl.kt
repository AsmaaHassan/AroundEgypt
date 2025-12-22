package com.example.github.data.repository

import com.example.aroundegypt.domain.model.Experience
import com.example.github.data.datasources.remote.api.ExperienceApi
import com.example.github.data.mappers.toDomain
import com.example.github.data.models.dto.ExperienceDto
import com.example.github.domain.repository.ExperienceRepository

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

class ExperienceRepositoryImpl(
    private val api: ExperienceApi
) : ExperienceRepository {
    override suspend fun getRecommendedExperience(): List<Experience> {
        val response: List<ExperienceDto>? = api.getRecommendedExperience().data
        response?.let {
            if (it.isNotEmpty()) {
                return response.map { it.toDomain() }
            }
        }
        return emptyList()

    }


    override suspend fun getRecentExperience(): List<Experience> {
        val response: List<ExperienceDto>? = api.getRecentExperience().data
        response?.let {
            if (it.isNotEmpty()) {
                return response.map { it.toDomain() }
            }
        }
        return emptyList()
    }

    override suspend fun searchExperience(searchText: String): List<Experience> {
        val response: List<ExperienceDto>? = api.searchExperience(searchText).data
        response?.let {
            if (it.isNotEmpty()) {
                return response.map { it.toDomain() }
            }
        }
        return emptyList()
    }

    override suspend fun likeExperience(experienceId: String): Int {
        val response: Int? = api.likeExperience(experienceId).data
        response?.let {
            return response

        }
        return -1
    }
}
