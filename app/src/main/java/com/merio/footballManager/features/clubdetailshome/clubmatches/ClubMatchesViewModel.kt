package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.Matches
import com.merio.footballManager.domain.data.repository.FMRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClubMatchesViewModel @Inject constructor(
    private val fmRepository: FMRepository
) : ViewModel() {

    val matchLiveData = MutableLiveData<List<Matches>>()
    private val compositeDisposable = CompositeDisposable()

    fun getMatches(seasonId: Int, teamId: Int) {
        compositeDisposable.add(
            fmRepository.getMatches(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d(R.string.Network_error.toString(), it.toString())
                }
                .doOnSuccess {
                    matchLiveData.value = it.filter {
                        it.home_team.team_id == teamId || it.away_team.team_id == teamId
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

