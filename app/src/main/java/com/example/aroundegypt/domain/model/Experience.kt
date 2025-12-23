package com.example.aroundegypt.domain.model

/**
 * Created by AsmaaHassan on 14,December,2025
 * Trufla Technology,
 * Cairo, Egypt.
 */
data class Experience(
    val id: String,
    val title: String,
    var likesNumber: Int,
    val coverPhoto: String,
    val location: String,
    val description: String,
    var isLiked: Boolean
)
