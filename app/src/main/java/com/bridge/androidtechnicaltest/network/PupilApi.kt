package com.bridge.androidtechnicaltest.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PupilApi {

    @GET("pupils")
    suspend fun getPupils(@Query("page") page: Int = 1): PagedResponse<PupilDto>

    @GET("pupils/{pupilId}")
    suspend fun getPupil(@Path("pupilId") pupilId: String): PupilDto

    @PUT("pupils/{pupilId}")
    suspend fun updatePupil(@Path("pupilId") pupilId: String, @Body pupil: PupilDto): PupilDto

    @POST("pupils")
    suspend fun createPupil(@Body pupil: PupilDto): PupilDto

    @DELETE("pupils/{pupilId}")
    suspend fun deletePupil(@Path("pupilId") pupilId: String)
}