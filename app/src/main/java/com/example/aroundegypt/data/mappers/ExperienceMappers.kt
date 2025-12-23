package com.example.aroundegypt.data.mappers

import com.example.aroundegypt.data.datasources.local.entity.ExperienceEntity
import com.example.aroundegypt.domain.model.Experience
import com.example.github.data.models.dto.ExperienceDto

fun ExperienceDto.toEntity(isRecommended: Boolean = false, isRecent: Boolean = false) = ExperienceEntity(
    id = id,
    title = title ?: "",
    likesNumber = likes_no ?: 0,
    coverPhoto = cover_photo ?: "",
    location = gmap_location?.toString() ?: "",
    description = detailed_description ?: "",
    isLiked = is_liked ?: false,
    isRecommended = isRecommended,
    isRecent = isRecent
)

fun ExperienceEntity.toDomain() = Experience(
    id = id,
    title = title,
    likesNumber = likesNumber,
    coverPhoto = coverPhoto,
    location = location,
    description = description,
    isLiked = isLiked
)
