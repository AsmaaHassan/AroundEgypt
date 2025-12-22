package com.example.github.data.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceRes(
    val data: List<ExperienceDto>? = null,
    val meta: Meta? = null,
    val pagination: Pagination? = null
)


@Serializable
data class LikeExperienceRes(
    val data: Int? = null,
    val meta: Meta? = null,
    val pagination: Pagination? = null
)

@Serializable
data class ExperienceDto(
    val address: String? = null,
    val audio_url: String? = null,
    val city: City? = null,
    val cover_photo: String? = null,
    val description: String? = null,
    val detailed_description: String? = null,
    val era: Era? = null,
    val experience_tips: List<Any?>? = null,
    val famous_figure: String? = null,
    val founded: String? = null,
    val gmap_location: GmapLocation? = null,
    val has_audio: Boolean? = null,
    val has_video: Int? = null,
    val id: String,
    val is_liked: Boolean? = null,
    val likes_no: Int? = null,
    val opening_hours: OpeningHours? = null,
    val period: Period? = null,
    val rating: Int? = null,
    val recommended: Int? = null,
    val reviews: List<Review?>? = null,
    val reviews_no: Int? = null,
    val starting_price: Int? = null,
    val tags: List<Tag?>? = null,
    val ticket_prices: List<TicketPrice?>? = null,
    val title: String? = null,
    val tour_html: String? = null,
    val translated_opening_hours: TranslatedOpeningHours? = null,
    val views_no: Int? = null
)

@Serializable
data class Meta(
    val code: Int? = null,
    val errors: List<Any?>? = null
)

@Serializable
class Pagination

@Serializable
data class City(
    val disable: Any? = null,
    val id: Int? = null,
    val name: String? = null,
    val top_pick: Int? = null
)

@Serializable
data class Era(
    val created_at: String? = null,
    val id: String,
    val updated_at: String? = null,
    val value: String? = null
)

@Serializable
data class GmapLocation(
    val coordinates: List<Double?>? = null,
    val type: String? = null
)

@Serializable
data class OpeningHours(
    val friday: List<String?>? = null,
    val monday: List<String?>? = null,
    val saturday: List<String?>? = null,
    val sunday: List<String?>? = null,
    val thursday: List<String?>? = null,
    val tuesday: List<String?>? = null,
    val wednesday: List<String?>? = null
)

@Serializable
data class Period(
    val created_at: String? = null,
    val id: String,
    val updated_at: String? = null,
    val value: String? = null
)

@Serializable
data class Review(
    val comment: String? = null,
    val created_at: String? = null,
    val experience: String? = null,
    val id: String,
    val name: String? = null,
    val rating: Int? = null
)

@Serializable
data class Tag(
    val disable: Any? = null,
    val id: Int? = null,
    val name: String? = null,
    val top_pick: Int? = null
)

@Serializable
data class TicketPrice(
    val price: Int? = null,
    val type: String? = null
)

@Serializable
data class TranslatedOpeningHours(
    val friday: Friday? = null,
    val monday: Monday? = null,
    val saturday: Saturday? = null,
    val sunday: Sunday? = null,
    val thursday: Thursday? = null,
    val tuesday: Tuesday? = null,
    val wednesday: Wednesday? = null
)

@Serializable
data class Friday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Monday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Saturday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Sunday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Thursday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Tuesday(
    val day: String? = null,
    val time: String? = null
)

@Serializable
data class Wednesday(
    val day: String? = null,
    val time: String? = null
)
