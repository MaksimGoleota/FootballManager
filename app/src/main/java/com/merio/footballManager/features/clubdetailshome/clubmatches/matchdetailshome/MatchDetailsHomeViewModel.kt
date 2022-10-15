package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.MatchInfo
import com.merio.footballManager.domain.data.repository.FMRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchDetailsHomeViewModel @Inject constructor(
    private val fmRepository: FMRepository,
    private val statisticsItemMapper: StatisticsItemMapper
) : ViewModel() {

    val matchInfoLiveData = MutableLiveData<MatchInfo>()
    val statisticsLiveData = MutableLiveData<List<StatisticsItem>>()
    private val compositeDisposable = CompositeDisposable()

    fun getMatchInfo(matchId: Int) {
        compositeDisposable.add(
            fmRepository.getMatchById(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d(R.string.Network_error.toString(), it.toString())
                }
                .doOnSuccess {
                    matchInfoLiveData.value = it
                    statisticsLiveData.value = statisticsItemMapper.map(
                        it.match_statistics.first(),
                        it.match_statistics.last()
                    )
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
