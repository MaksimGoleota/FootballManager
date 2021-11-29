package com.merio.footballManager.features.leagueteams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.domain.data.network.models.Teams
import com.merio.footballManager.domain.data.repository.FMRepository
import com.merio.footballManager.domain.data.repository.TeamsDatabaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LeagueTeamsViewModel @Inject constructor(
    private val fmRepository : FMRepository,
    private val teamsDatabaseRepository: TeamsDatabaseRepository
): ViewModel() {

    val teamsLiveData = MutableLiveData<List<Teams>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTeams(countryId: Int) {
        compositeDisposable.add(
            fmRepository.getCountryTeams(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("1111111111111111111", it.toString())
                }
                .doOnSuccess {
                    teamsLiveData.value = it
                    teamsDatabaseRepository.addAllTeams(it)
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
