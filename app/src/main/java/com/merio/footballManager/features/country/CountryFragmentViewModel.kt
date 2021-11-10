package com.merio.footballManager.features.country

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.domain.data.network.models.Country
import com.merio.footballManager.domain.data.repository.FMRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryFragmentViewModel @Inject constructor(
    private val fmRepository : FMRepository
) : ViewModel() {

    val countryLiveData = MutableLiveData<List<Country>>()

    private val compositeDisposable = CompositeDisposable()

    fun getAllCountries() {
        compositeDisposable.add(
            fmRepository.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                   Log.d("1111111111111111111", it.toString())
                }
                .doOnSuccess {
                    countryLiveData.value = it
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
