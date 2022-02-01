package com.merio.footballManager.domain.data.repository

import com.merio.footballManager.domain.data.mapper.TeamMapper
import com.merio.footballManager.domain.data.network.api.FMApiClient
import com.merio.footballManager.domain.data.network.models.*
import io.reactivex.Single
import javax.inject.Inject


class FMRepository @Inject constructor(
    private val fmApiClient: FMApiClient,
    private val teamMapper: TeamMapper
) {

    fun getCountryTeams(countryId: Int): Single<List<Teams>> {
        return fmApiClient.getTeams(countryId)
            .map { response ->
                teamMapper.map(response.data)
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

    fun getMatches(seasonId: Int): Single<List<Matches>> {
        return fmApiClient.getMatches(seasonId)
            .map { response ->
                response.data
            }
    }

    fun getMatchById(matchId: Int): Single<MatchInfo> {
        return fmApiClient.getMatchById(matchId)
            .map { response ->
                response.data
            }
    }

    fun getTopScorers(seasonId: Int): Single<List<TopScorers>> {
        return fmApiClient.getTopScorers(seasonId)
            .map { response ->
                response.data
            }
    }
}
