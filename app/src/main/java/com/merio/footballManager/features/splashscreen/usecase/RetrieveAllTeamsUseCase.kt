package com.merio.footballManager.features.splashscreen.usecase

import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import com.merio.footballManager.domain.data.repository.FMRepository
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RetrieveAllTeamsUseCase @Inject constructor(
    private val fmRepository: FMRepository,
    private val teamsDatabaseRepository: TeamsDatabaseRepository
) {
    fun execute(): Completable {
        return Single.zip(
            fmRepository.getCountryTeams(countryId = ENGLAND_ID),
            fmRepository.getCountryTeams(countryId = SPAIN_ID)
        ) { englandTeams, spainTeams ->
            teamsDatabaseRepository.apply {
                addAllTeams(englandTeams)
                addAllTeams(spainTeams)
            }
        }.ignoreElement()
    }
}
