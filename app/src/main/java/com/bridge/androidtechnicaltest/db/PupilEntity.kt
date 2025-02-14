package com.bridge.androidtechnicaltest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bridge.androidtechnicaltest.domain.Pupil
import com.bridge.androidtechnicaltest.network.PupilDto

@Entity(tableName = "Pupils")
data class PupilEntity(
    @PrimaryKey
    @ColumnInfo(name = "pupil_id")
    val pupilId: Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "page")
    var page: Int? = 1,
    @ColumnInfo(name = "is_synced")
    var isSynced: Boolean = true,
    @ColumnInfo(name = "is_new")
    var isNew: Boolean = false
){
    fun toDomain(): Pupil {
        return Pupil(
            pupilId = pupilId,
            name = name,
            country = country,
            image = image,
            latitude = latitude,
            longitude = longitude
        )
    }

    fun toDto(): PupilDto {
        return PupilDto(
            country = country,
            image = image,
            latitude = latitude,
            longitude = longitude,
            name = name,
            pupilId = pupilId
        )
    }
}