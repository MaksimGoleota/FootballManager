package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.MatchStatistics
import javax.inject.Inject

class StatisticsItemMapper @Inject constructor() {

    fun map(
        homeTeamStatistics: MatchStatistics,
        awayTeamStatistics: MatchStatistics
    ): List<StatisticsItem> {
        val statisticsItems = mutableListOf<StatisticsItem>()
        statisticsItems.apply {
            add(
                StatisticsItem(
                    statisticsLabel = R.string.shots_total,
                    homeTeamValue = homeTeamStatistics.shots_total.toString(),
                    awayTeamValue = awayTeamStatistics.shots_total.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.shots_on_target,
                    homeTeamValue = homeTeamStatistics.shots_on_target.toString(),
                    awayTeamValue = awayTeamStatistics.shots_on_target.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.shots_off_target,
                    homeTeamValue = homeTeamStatistics.shots_off_target.toString(),
                    awayTeamValue = awayTeamStatistics.shots_off_target.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.shots_blocked,
                    homeTeamValue = homeTeamStatistics.shots_blocked.toString(),
                    awayTeamValue = awayTeamStatistics.shots_blocked.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.possession_percent,
                    homeTeamValue = "${homeTeamStatistics.possessionpercent}%",
                    awayTeamValue = "${awayTeamStatistics.possessionpercent}%"
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.yellow_cards,
                    homeTeamValue = homeTeamStatistics.yellow_cards.toString(),
                    awayTeamValue = awayTeamStatistics.yellow_cards.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.red_cards,
                    homeTeamValue = homeTeamStatistics.red_cards.toString(),
                    awayTeamValue = awayTeamStatistics.red_cards.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.fouls,
                    homeTeamValue = homeTeamStatistics.fouls.toString(),
                    awayTeamValue = awayTeamStatistics.fouls.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.corners,
                    homeTeamValue = homeTeamStatistics.corners.toString(),
                    awayTeamValue = awayTeamStatistics.corners.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.offsides,
                    homeTeamValue = homeTeamStatistics.offsides.toString(),
                    awayTeamValue = awayTeamStatistics.offsides.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.attacks,
                    homeTeamValue = homeTeamStatistics.attacks.toString(),
                    awayTeamValue = awayTeamStatistics.attacks.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.goal_attempts,
                    homeTeamValue = homeTeamStatistics.goal_attempts.toString(),
                    awayTeamValue = awayTeamStatistics.goal_attempts.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.penalties,
                    homeTeamValue = homeTeamStatistics.penalties.toString(),
                    awayTeamValue = awayTeamStatistics.penalties.toString()
                )
            )
            add(
                StatisticsItem(
                    statisticsLabel = R.string.dangerous_attacks,
                    homeTeamValue = homeTeamStatistics.dangerous_attacks.toString(),
                    awayTeamValue = awayTeamStatistics.dangerous_attacks.toString()
                )
            )
        }
        return statisticsItems
    }
}

