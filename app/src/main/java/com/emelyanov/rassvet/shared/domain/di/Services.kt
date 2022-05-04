package com.emelyanov.rassvet.shared.domain.di

import android.content.Context
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.AuthorizationService
import com.emelyanov.rassvet.shared.domain.services.authorizationservice.IAuthorizationService
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.BASE_URL
import com.emelyanov.rassvet.shared.domain.services.rassvetApi.IRassvetApi
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.FakeSectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.ISectionsRepository
import com.emelyanov.rassvet.shared.domain.services.sectionsrepository.SectionsRepository
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.ITokensStorage
import com.emelyanov.rassvet.shared.domain.services.tokenstorage.TokensStorage
import com.emelyanov.rassvet.shared.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
class Services {
    @Singleton
    @Provides
    fun provideRassvetApi(): IRassvetApi
    = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(IRassvetApi::class.java)

    @Singleton
    @Provides
    fun provideSectionsRepository(
        rassvetApi: IRassvetApi
    ): ISectionsRepository
    = SectionsRepository(
        rassvetApi
    )

    @Singleton
    @Provides
    fun provideTokensStorage(@ApplicationContext appContext: Context): ITokensStorage = TokensStorage(appContext)

    @Singleton
    @Provides
    fun provideAuthorizationService(
        rassvetApi: IRassvetApi,
        saveTokensUseCase: SaveTokensUseCase,
        getAuthHeaderUseCase: GetAuthHeaderUseCase,
        getRefreshTokenUseCase: GetRefreshTokenUseCase,
        provideAuthedRequestUseCase: ProvideAuthedRequestUseCase,
        clearTokensUseCase: ClearTokensUseCase
    ): IAuthorizationService
    = AuthorizationService(
        rassvetApi,
        saveTokensUseCase,
        getRefreshTokenUseCase,
        getAuthHeaderUseCase,
        provideAuthedRequestUseCase,
        clearTokensUseCase
    )
}