package com.emelyanov.rassvet.shared.domain.di

import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.FakeSectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.ISectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.SectionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://10.147.17.238:3000"

@InstallIn(SingletonComponent::class)
@Module
class Services {
    @Singleton
    @Provides
    fun provideSectionsRepository(rassvetApi: IRassvetApi): ISectionsRepository
        = SectionsRepository(rassvetApi)

    @Singleton
    @Provides
    fun provideRassvetApi(): IRassvetApi
        = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(IRassvetApi::class.java)
}