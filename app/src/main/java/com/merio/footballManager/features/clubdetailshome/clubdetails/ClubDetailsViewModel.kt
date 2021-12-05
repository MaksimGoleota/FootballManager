package com.merio.footballManager.features.clubdetailshome.clubdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.domain.data.network.models.Teams
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClubDetailsViewModel @Inject constructor(
    private val teamsDatabaseRepository: TeamsDatabaseRepository
): ViewModel() {

    val leagueTeamsLiveData = MutableLiveData<Teams>()
    private val compositeDisposable = CompositeDisposable()

    fun getDetails(teamId: Int) {
        compositeDisposable.add(
            teamsDatabaseRepository.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("1111111111111111111", it.toString())
                }
                .doOnSuccess {
                    leagueTeamsLiveData.value = it.first {
                        it.team_id == teamId
                    }
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
