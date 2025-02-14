package com.bridge.androidtechnicaltest.network

import com.bridge.androidtechnicaltest.db.PupilEntity

data class PupilDto(
    val country: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val pupilId: Int? = null
)

fun PupilDto.toEntity(): PupilEntity {
    return PupilEntity(
        country = country,
        image = image,
        latitude = latitude,
        longitude = longitude,
        name = name,
        pupilId = pupilId
    )
}

