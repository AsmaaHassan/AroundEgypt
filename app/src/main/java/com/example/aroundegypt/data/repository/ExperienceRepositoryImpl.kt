package com.example.github.data.repository

import com.example.aroundegypt.data.datasources.local.dao.ExperienceDao
import com.example.aroundegypt.data.mappers.toEntity
import com.example.aroundegypt.domain.model.Experience
import com.example.github.data.datasources.remote.api.ExperienceApi
import com.example.github.domain.repository.ExperienceRepository
import com.example.aroundegypt.data.mappers.toDomain as toDomainFromEntity
import com.example.github.data.mappers.toDomain as toDomainFromDto

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

class ExperienceRepositoryImpl(
    private val api: ExperienceApi,
    private val dao: ExperienceDao
) : ExperienceRepository {
    override suspend fun getRecommendedExperience(): List<Experience> {
        try {
            val response = api.getRecommendedExperience().data
            if (!response.isNullOrEmpty()) {
                dao.upsertExperiences(response.map { it.toEntity(isRecommended = true) }, isRecommended = true)
            }
        } catch (e: Exception) {
            // Log error
        }
        return dao.getRecommendedExperiences().map { it.toDomainFromEntity() }
    }


    override suspend fun getRecentExperience(): List<Experience> {
        try {
            val response = api.getRecentExperience().data
            if (!response.isNullOrEmpty()) {
                dao.upsertExperiences(response.map { it.toEntity(isRecent = true) }, isRecent = true)
            }
        } catch (e: Exception) {
            // Log error
        }
        return dao.getRecentExperiences().map { it.toDomainFromEntity() }
    }

    override suspend fun searchExperience(searchText: String): List<Experience> {
        return try {
            val response = api.searchExperience(searchText).data
            response?.map { it.toDomainFromDto() } ?: emptyList()
        } catch (e: Exception) {
            dao.searchExperiences(searchText).map { it.toDomainFromEntity() }
        }
    }

    override suspend fun likeExperience(experienceId: String): Int {
        return try {
            val response = api.likeExperience(experienceId).data ?: -1
            if (response != -1) {
                val currentExperiences = dao.getRecentExperiences() + dao.getRecommendedExperiences()
                val exp = currentExperiences.find { it.id == experienceId }
                exp?.let {
                    dao.updateLikeStatus(experienceId, response, !it.isLiked)
                }
            }
            response
        } catch (e: Exception) {
            -1
        }
    }
}
