package com.bridge.androidtechnicaltest.domain

import com.bridge.androidtechnicaltest.db.PupilEntity

data class Pupil(
    val pupilId: Int?,
    val name: String,
    val country: String,
    val image: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    fun toEntity(): PupilEntity {
        return PupilEntity(
            pupilId = pupilId,
            name = name,
            country = country,
            image = image,
            latitude = latitude,
            longitude = longitude,
            isSynced = false
        )
    }
}