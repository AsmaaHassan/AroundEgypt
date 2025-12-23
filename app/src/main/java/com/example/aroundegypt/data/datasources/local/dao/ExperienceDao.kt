package com.example.aroundegypt.data.datasources.local.dao

import androidx.room.*
import com.example.aroundegypt.data.datasources.local.entity.ExperienceEntity

@Dao
interface ExperienceDao {
    @Query("SELECT * FROM experiences WHERE isRecommended = 1")
    suspend fun getRecommendedExperiences(): List<ExperienceEntity>

    @Query("SELECT * FROM experiences WHERE isRecent = 1")
    suspend fun getRecentExperiences(): List<ExperienceEntity>

    @Query("SELECT * FROM experiences WHERE title LIKE '%' || :query || '%'")
    suspend fun searchExperiences(query: String): List<ExperienceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExperiences(experiences: List<ExperienceEntity>): List<Long>

    @Query("UPDATE experiences SET title = :title, likesNumber = :likesNumber, coverPhoto = :coverPhoto, location = :location, description = :description, isLiked = :isLiked WHERE id = :id")
    suspend fun updateExperienceData(id: String, title: String, likesNumber: Int, coverPhoto: String, location: String, description: String, isLiked: Boolean)

    @Query("UPDATE experiences SET isRecommended = :isRecommended WHERE id = :id")
    suspend fun setRecommended(id: String, isRecommended: Boolean)

    @Query("UPDATE experiences SET isRecent = :isRecent WHERE id = :id")
    suspend fun setRecent(id: String, isRecent: Boolean)

    @Query("UPDATE experiences SET likesNumber = :likesNumber, isLiked = :isLiked WHERE id = :id")
    suspend fun updateLikeStatus(id: String, likesNumber: Int, isLiked: Boolean)

    @Transaction
    suspend fun upsertExperiences(experiences: List<ExperienceEntity>, isRecommended: Boolean = false, isRecent: Boolean = false) {
        for (exp in experiences) {
            insertExperiences(listOf(exp))
            updateExperienceData(
                exp.id, exp.title, exp.likesNumber, exp.coverPhoto, 
                exp.location, exp.description, exp.isLiked
            )
            
            if (isRecommended) setRecommended(exp.id, true)
            if (isRecent) setRecent(exp.id, true)
        }
    }
}
