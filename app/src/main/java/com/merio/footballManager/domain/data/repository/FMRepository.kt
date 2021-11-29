package com.merio.footballManager.domain.data.repository

import com.merio.footballManager.domain.data.network.api.FMApiClient
import com.merio.footballManager.domain.data.network.models.Standings
import com.merio.footballManager.domain.data.network.models.Table
import com.merio.footballManager.domain.data.network.models.Teams
import io.reactivex.Single
import javax.inject.Inject


class FMRepository @Inject constructor(
    private val fmApiClient: FMApiClient
) {

    fun getCountryTeams(countryId: Int): Single<List<Teams>> {
        return fmApiClient.getTeams(countryId)
            .map { response ->
                response.data
            }
    }

//    fun getPremierLeagueSeasons(): Single<List<Seasons>> {
//        return fmApiClient.getSeasons(PREMIER_LEAGUE_ID)
//            .map { response ->
//                response.data
//            }
//    }

    fun getTable(seasonId: Int): Single<List<Standings>> {
        return fmApiClient.getTable(seasonId)
            .map { response ->
                response.data.standings
            }
    }
}
