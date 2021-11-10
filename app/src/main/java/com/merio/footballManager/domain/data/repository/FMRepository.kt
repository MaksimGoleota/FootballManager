package com.merio.footballManager.domain.data.repository

import com.merio.footballManager.domain.data.network.api.FMApiClient
import com.merio.footballManager.domain.data.network.models.Country
import io.reactivex.Single
import javax.inject.Inject

class FMRepository @Inject constructor(
    private val fmApiClient: FMApiClient
) {

    fun getCountries(): Single<List<Country>> {
        return fmApiClient.getCountries()
            .map { response ->
                response.data
            }
    }
}
