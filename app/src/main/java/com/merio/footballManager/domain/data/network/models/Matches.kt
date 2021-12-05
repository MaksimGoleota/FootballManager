package com.merio.footballManager.domain.data.network.models

import java.text.SimpleDateFormat

data class MatchesResponse(
    val query: MatchesQuery,
    val data: List<Matches>
)

data class MatchesQuery(
    val season_id: String,
    val date_from: String,
    val apikey: String
)

data class Matches(
    val match_id: Int,
    val status_code: Int,
    val status: String,
    var match_start: String,
    val match_start_iso: String,
    val minute: Int,
    val league_id: Int,
    val season_id: Int,
    val stage: Stage,
    val group: Group,
    val round: Round,
    val referee_id: String?,
    val home_team: HomeTeam,
    val away_team: AwayTeam,
    val stats: Stats,
    val venue: Venue
) {
    fun getFormattedData(): String {
        val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm")

        val df2 = SimpleDateFormat("dd/MM/yy")

        match_start = df2.format(format.parse(match_start))

//        val format = SimpleDateFormat("dd/MM/yy")
//        match_start = match_start.convertTo(format)

        return match_start
    }
}

data class Stage(
    val stage_id: Int,
    val name: String
)

data class Group(
    val group_id: Int,
    val group_name: String
)

data class Round(
    val round_id: Int,
    val name: String,
    val is_current: String?
)

data class HomeTeam(
    val team_id: Int,
    val name: String,
    val short_code: String,
    val common_name: String?,
    var logo: String?,
    val country: Country
) {
    fun getFormattedProfilePath(): String? {
        logo = logo?.replace("\\/", "/", ignoreCase = false)
        return logo
    }
}

data class AwayTeam(
    val team_id: Int,
    val name: String,
    val short_code: String,
    val common_name: String?,
    var logo: String?,
    val country: Country
) {
    fun getFormattedProfilePath(): String? {
        logo = logo?.replace("\\/", "/", ignoreCase = false)
        return logo
    }
}

data class Stats(
    val home_score: Int,
    val away_score: Int,
    val ht_score: String,
    val ft_score: String,
    val et_score: String?,
    val ps_score: String?,
)

data class Venue(
    val venue_id: Int,
    val name: String,
    val capacity: Int,
    val city: String,
    val country_id: Int
)
