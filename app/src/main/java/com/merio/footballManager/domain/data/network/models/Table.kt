package com.merio.footballManager.domain.data.network.models

data class TableResponse(
    val query: TableQuery,
    val data: Table
)

data class TableQuery(
    val apikey: String,
    val season_id: String
)

data class Table(
    val season_id: Int,
    val league_id: Int,
    val has_groups: Int,
    val standings: List<Standings>
)

data class Standings(
    val team_id: Int,
    val position: Int,
    val points: Int,
    val status: String,
    val result: String?,
    val overall: Overall,
    val home: Home,
    val away: Away
)

data class TableTeam(
    val team: Teams,
    val position: Int,
    val points: Int,
    val status: String,
    val result: String?,
    val overall: Overall,
    val home: Home,
    val away: Away
)

data class Overall(
    val games_played: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val goals_diff: Int,
    val goals_scored: Int,
    val goals_against: Int
)

data class Home(
    val games_played: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val goals_diff: Int,
    val goals_scored: Int,
    val goals_against: Int
)

data class Away(
    val games_played: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val goals_diff: Int,
    val goals_scored: Int,
    val goals_against: Int
)
