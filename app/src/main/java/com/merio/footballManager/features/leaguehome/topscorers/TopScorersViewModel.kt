package com.merio.footballManager.features.leaguehome.topscorers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.TopScorer
import com.merio.footballManager.domain.data.repository.FMRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopScorersViewModel @Inject constructor(
    private val fmRepository: FMRepository
) : ViewModel() {

    val topScorersLiveData = MutableLiveData<List<TopScorer>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTopScorers(seasonId: Int) {
        compositeDisposable.add(
            fmRepository.getTopScorers(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d(R.string.Network_error.toString(), it.toString())
                }
                .doOnSuccess {
                    topScorersLiveData.value = it
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
