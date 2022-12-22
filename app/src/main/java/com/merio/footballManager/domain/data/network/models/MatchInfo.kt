package com.merio.footballManager.domain.data.network.models

data class MatchByIdResponse(
    val query: MatchByIdQuery,
    val data: MatchInfo
)

data class MatchByIdQuery(
    val apikey: String
)

data class MatchInfo(
    val match_id: Int,
    val league_id: Int,
    val round: Round,
    val referee_id: Int,
    val season_id: Int,
    val stage: Stage,
    val group: Group,
    val status_code: Int,
    val match_start: String,
    val match_start_iso: String,
    val minute: Int,
    val status: String,
    val stats: Stats,
    val home_team: HomeTeam,
    val away_team: AwayTeam,
    val match_events: List<MatchEvents>,
    val match_statistics: List<MatchStatistics>,
    val lineups: List<Lineups>,
    val venue: Venue
)

data class MatchEvents(
    val team_id: Int,
    val type: String?,
    val player_id: Int,
    val player_name: String,
    val related_player_id: Int?,
    val related_player_name: String?,
    val minute: Int,
    val extra_minute: Int?,
    val reason: Int?,
    val injured: String?,
    val own_goal: String?,
    val penalty: String?,
    val result: String?,
)

data class MatchStatistics(
    val team_id: Int,
    val team_name: String,
    val fouls: Int,
    val injuries: Int,
    val corners: Int,
    val offsides: Int,
    val shots_total: Int,
    val shots_on_target: Int,
    val shots_off_target: Int,
    val shots_blocked: Int,
    val possession_time: Int,
    val possessionpercent: Int,
    val yellow_cards: Int,
    val yellow_red_cards: Int,
    val red_cards: Int,
    val substitutions: Int,
    val goal_kick: Int,
    val goal_attempts: Int,
    val free_kick: Int,
    val throw_in: Int,
    val ball_safe: Int,
    val goals: Int,
    val penalties: Int,
    val attacks: Int,
    val dangerous_attacks: Int,
)

data class Lineups(
    val team_id: Int,
    val formation: String?,
    val formation_confirmed: Int?,
    val players: List<Players>
)

data class Players(
    val player_id: Int,
    val firstname: String,
    val lastname: String,
    val birthday: String,
    val age: Int,
    val weight: Int,
    val height: Int,
    val img: String?,
    val country: Country,
    val detailed: Detailed
)

data class Detailed(
    val number: Int,
    val position: String,
    val order: String?
)
