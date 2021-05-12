package com.zaidzakir.mediumexample.dependencyInjection

import com.zaidzakir.mediumexample.model.api.KanyeWestApi
import com.zaidzakir.mediumexample.repository.DefualtMainRepository
import com.zaidzakir.mediumexample.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *Created by Zaid Zakir
 */
private const val BASE_URL = "https://api.kanye.rest/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{

    @Singleton
    @Provides
    fun provideKanyeWestApi():KanyeWestApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KanyeWestApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(kanyewestapi:KanyeWestApi) : MainRepository = DefualtMainRepository(kanyewestapi)

}