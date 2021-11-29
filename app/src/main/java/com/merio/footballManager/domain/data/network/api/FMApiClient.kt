package com.merio.footballManager.domain.data.network.api

import com.merio.footballManager.domain.data.network.models.TableResponse
import com.merio.footballManager.domain.data.network.models.TeamsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FMApiClient {

    @GET("soccer/teams")
    fun getTeams(
        @Query("country_id") countryId: Int
    ): Single<TeamsResponse>

//    @GET("soccer/seasons")
//    fun getSeasons(
//        @Query("league_id" ) leagueId: Int
//    ): Single<SeasonsResponse>

    @GET("soccer/standings")
    fun getTable(
        @Query("season_id") seasonId: Int
    ): Single<TableResponse>
}
