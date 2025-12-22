package com.example.github.data.mappers

import com.example.aroundegypt.domain.model.Experience
import com.example.github.data.models.dto.ExperienceDto
import kotlin.Boolean

/**
 * Created by AsmaaHassan on 16,November,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */


fun ExperienceDto.toDomain() = Experience(
    id=id,
    title = title?:"",
    likesNumber = likes_no?:0,
    coverPhoto = cover_photo?:"",
    location = gmap_location.toString(),
    description = detailed_description?:"",
    isLiked= is_liked?:false
)

