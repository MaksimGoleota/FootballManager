package com.merio.footballManager.features.splashscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.merio.footballManager.features.splashscreen.usecase.RetrieveAllTeamsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val retrieveAllTeamsUseCase: RetrieveAllTeamsUseCase
) : ViewModel() {

    val statusLiveData = MutableLiveData<Status>()
    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(
            retrieveAllTeamsUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.d("Network Error:", it.toString())
                    statusLiveData.value = Status.Error
                }
                .doOnComplete {
                    statusLiveData.value = Status.Completed
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    sealed class Status {
        object Completed : Status()
        object Error : Status()
    }
}
