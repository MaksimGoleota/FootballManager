package com.merio.footballManager.features.leaguehome.table

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.merio.footballManager.features.leaguehome.table.usecase.GetTableUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LeagueTableViewModel @Inject constructor(
    private val getTableUsecase : GetTableUsecase
): ViewModel() {

    val leagueTableLiveData = MutableLiveData<List<TableTeam>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTable(seasonId: Int) {
        compositeDisposable.add(
            getTableUsecase.execute(seasonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("1111111111111111111", it.toString())
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
