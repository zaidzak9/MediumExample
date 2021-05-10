package com.zaidzakir.mediumexample.repository

import com.zaidzakir.mediumexample.model.KanyeWestApiResponse
import com.zaidzakir.mediumexample.model.Resource

/**
 *Created by Zaid Zakir
 */
interface MainRepository {
    suspend fun getQuotes(): Resource<KanyeWestApiResponse>
}