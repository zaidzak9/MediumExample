package com.zaidzakir.mediumexample.repository

import com.zaidzakir.mediumexample.model.api.KanyeWestApi
import com.zaidzakir.mediumexample.model.KanyeWestApiResponse
import com.zaidzakir.mediumexample.model.Resource
import java.lang.Exception
import javax.inject.Inject

/**
 *Created by Zaid Zakir
 */
class DefualtMainRepository @Inject constructor(
    val kanyeWestApi: KanyeWestApi
):MainRepository {
    override suspend fun getQuotes(): Resource<KanyeWestApiResponse> {
        return try {
            val response = kanyeWestApi.getQuotes()
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error("An Error occurred")
            }
        }catch (e:Exception){
            println("kanyeWestApi $e")
            Resource.Error("An $e occurred")
        }
    }
}