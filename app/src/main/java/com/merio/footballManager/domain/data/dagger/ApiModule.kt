package com.merio.footballManager.domain.data.dagger

import com.merio.footballManager.domain.data.network.api.FMApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun providesFMApiClient(retrofit: Retrofit) = retrofit.create(FMApiClient::class.java)
}
