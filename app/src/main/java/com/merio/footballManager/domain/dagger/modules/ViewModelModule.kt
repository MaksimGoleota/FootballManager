package com.merio.footballManager.domain.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.dagger.factory.ViewModelKey
import com.merio.footballManager.features.country.CountryFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CountryFragmentViewModel::class)
    fun countryFragmentViewModel(actorsListViewModel: CountryFragmentViewModel): ViewModel
}
