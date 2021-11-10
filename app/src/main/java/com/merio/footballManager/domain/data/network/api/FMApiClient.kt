package com.merio.footballManager.domain.data.network.api

import com.merio.footballManager.domain.data.network.models.CountryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FMApiClient {

    @GET("soccer/countries")
    fun getCountries(): Single<CountryResponse>
}
