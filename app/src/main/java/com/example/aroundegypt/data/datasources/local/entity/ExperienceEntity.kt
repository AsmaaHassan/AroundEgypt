package com.example.aroundegypt.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experiences")
data class ExperienceEntity(
    @PrimaryKey val id: String,
    val title: String,
    val likesNumber: Int,
    val coverPhoto: String,
    val location: String,
    val description: String,
    val isLiked: Boolean,
    val isRecommended: Boolean,
    val isRecent: Boolean
)
