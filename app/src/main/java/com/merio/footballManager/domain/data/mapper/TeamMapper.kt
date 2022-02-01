package com.merio.footballManager.domain.data.mapper

import com.merio.footballManager.domain.data.network.models.TeamDTO
import com.merio.footballManager.domain.data.network.models.Teams
import javax.inject.Inject

class TeamMapper @Inject constructor() {
    fun map(teams: List<TeamDTO>): List<Teams> {
        return teams.map {
            Teams(
                team_id = it.team_id,
                name = it.name,
                short_code = it.short_code,
                logo =  it.logo,
                countryId = it.country.country_id
            )
        }
    }
}
