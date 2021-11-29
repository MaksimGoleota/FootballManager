package com.merio.footballManager.domain.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.dagger.factory.ViewModelKey
import com.merio.footballManager.features.leagueteams.LeagueTeamsViewModel
import com.merio.footballManager.features.table.LeagueTableViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LeagueTeamsViewModel::class)
    fun premierLeagueHomeViewModel(actorsListViewModel: LeagueTeamsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeagueTableViewModel::class)
    fun premierLeagueTableViewModel(actorsListViewModel: LeagueTableViewModel): ViewModel
}
