package com.merio.footballManager.features.leaguehome.table

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.merio.footballManager.features.leaguehome.table.usecase.GetTableUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LeagueTableViewModel @Inject constructor(
    private val getTableUseCase : GetTableUseCase
): ViewModel() {

    val leagueTableLiveData = MutableLiveData<List<TableTeam>>()
    private val compositeDisposable = CompositeDisposable()

    fun getTable(countryId: Int) {
        compositeDisposable.add(
            getTableUseCase.execute(countryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d(R.string.Network_error.toString(), it.toString())
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
