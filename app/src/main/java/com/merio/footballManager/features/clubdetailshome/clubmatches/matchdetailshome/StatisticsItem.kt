package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import androidx.annotation.StringRes

data class StatisticsItem(
    @StringRes val statisticsLabel: Int,
    val homeTeamValue: String,
    val awayTeamValue: String
)
