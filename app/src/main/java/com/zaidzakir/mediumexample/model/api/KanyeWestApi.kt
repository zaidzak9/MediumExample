package com.zaidzakir.mediumexample.model.api

import com.zaidzakir.mediumexample.model.KanyeWestApiResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *Created by Zaid Zakir
 */
interface KanyeWestApi {
    @GET(".")
    suspend fun getQuotes():Response<KanyeWestApiResponse>
}