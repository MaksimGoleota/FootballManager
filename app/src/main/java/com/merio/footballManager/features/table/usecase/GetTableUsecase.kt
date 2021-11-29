package com.merio.footballManager.features.table.usecase

import com.merio.footballManager.domain.data.network.models.TableTeam
import com.merio.footballManager.domain.data.repository.FMRepository
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTableUsecase @Inject constructor(
    private val tableDatabaseRepository: TeamsDatabaseRepository,
    private val fmRepository: FMRepository
){
    fun execute(seasonId: Int): Single<List<TableTeam>> {
        return Single.zip(
            tableDatabaseRepository.getAllTeams(),
            fmRepository.getTable(seasonId)
        ) { teams, table ->
            table.map { standing ->
                TableTeam(
                    team = teams.find { it.team_id == standing.team_id }!!,
                    position = standing.position,
                    points = standing.points,
                    status = standing.status,
                    result = standing.result,
                    overall = standing.overall,
                    home = standing.home,
                    away = standing.away
                )
            }
        }
    }
}
