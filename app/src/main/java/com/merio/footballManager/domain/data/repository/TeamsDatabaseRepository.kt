package com.merio.footballManager.domain.data.repository

import com.merio.footballManager.domain.data.database.TeamsDatabase
import com.merio.footballManager.domain.data.network.models.Teams
import io.reactivex.Single
import javax.inject.Inject

class TeamsDatabaseRepository @Inject constructor(
    private val database: TeamsDatabase
) {
    fun addAllTeams(teams: List<Teams>) = database.dao().addTeams(teams)

    fun getAllTeams(): Single<List<Teams>> = database.dao().getAllTeams()
}
