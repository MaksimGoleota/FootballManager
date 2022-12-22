package com.merio.footballManager.features.common.usecase

import com.merio.footballManager.domain.data.network.models.Teams
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCountryTeamsUseCase @Inject constructor(
    private val teamsDatabaseRepository: TeamsDatabaseRepository
) {
    fun execute(countryId: Int): Single<List<Teams>> {
        return teamsDatabaseRepository.getCountryTeams(countryId)
    }
}
