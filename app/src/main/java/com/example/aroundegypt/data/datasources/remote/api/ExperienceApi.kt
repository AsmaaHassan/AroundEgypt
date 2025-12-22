package com.example.github.data.datasources.remote.api

import com.example.github.data.models.dto.ExperienceRes
import com.example.github.data.models.dto.LikeExperienceRes
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */

interface ExperienceApi {
    @GET("experiences?filter[recommended]=true")
    suspend fun getRecommendedExperience(
    ): ExperienceRes

    @GET("experiences")
    suspend fun getRecentExperience(
    ): ExperienceRes

    @GET("/api/v2/experiences")
    suspend fun searchExperience(
        @Query("filter[title]") searchText: String
    ): ExperienceRes

    @POST("/api/v2/experiences/{id}/like")
    suspend fun likeExperience(
        @Path("id") id: String
    ): LikeExperienceRes

}