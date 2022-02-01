package com.merio.footballManager.features.clubdetailshome.clubdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.merio.footballManager.domain.data.network.models.Teams
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import com.merio.footballManager.features.leaguehome.table.usecase.GetTableUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClubDetailsViewModel @Inject constructor(
    private val teamsDatabaseRepository: TeamsDatabaseRepository,
    private val getTableUseCase: GetTableUseCase
) : ViewModel() {

    val leagueTeamsLiveData = MutableLiveData<Teams>()
    val leagueTableLiveData = MutableLiveData<List<TableTeam>>()
    private val compositeDisposable = CompositeDisposable()

    fun getDetails(teamId: Int) {
        compositeDisposable.add(
            teamsDatabaseRepository.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("Network error", it.toString())
                }
                .doOnSuccess {
                    leagueTeamsLiveData.value = it.first {
                        it.team_id == teamId
                    }
                }
                .subscribe()
        )
    }

    fun getTable(seasonId: Int) {
        compositeDisposable.add(
            getTableUseCase.execute(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("Network error", it.toString())
                }
                .doOnSuccess {
                    leagueTableLiveData.value = it
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
