package com.merio.footballManager.domain.data.network.models

data class TopScorersResponse(
    val query: TopScorersQuery,
    val data: List<TopScorer>
)

data class TopScorersQuery(
    val apikey: String,
    val season_id: Int
)

data class TopScorer(
    val pos: Int,
    val player: Player,
    val team: Team,
    val league_id: Int,
    val season_id: Int,
    val matches_played: Int,
    val minutes_played: Int,
    val substituted_in: String?,
    val goals: Goals,
    val penalties: Int,
)

data class Player(
    val player_id: Int,
    val player_name: String,
)

data class Team(
    val team_id: Int,
    val team_name: String,
)

data class Goals(
    val overall: Int,
    val home: Int,
    val away: Int,
)
