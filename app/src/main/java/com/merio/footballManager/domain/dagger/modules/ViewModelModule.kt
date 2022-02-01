package com.merio.footballManager.domain.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.dagger.factory.ViewModelKey
import com.merio.footballManager.features.clubdetailshome.clubdetails.ClubDetailsViewModel
import com.merio.footballManager.features.clubdetailshome.clubmatches.ClubMatchesViewModel
import com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome.MatchDetailsHomeViewModel
import com.merio.footballManager.features.clubdetailshome.clubstatistics.ClubStatisticsViewModel
import com.merio.footballManager.features.leaguehome.table.LeagueTableViewModel
import com.merio.footballManager.features.leaguehome.topscorers.TopScorersViewModel
import com.merio.footballManager.features.splashscreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    fun splashScreenViewModel(actorsListViewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LeagueTableViewModel::class)
    fun leagueTableViewModel(actorsListViewModel: LeagueTableViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopScorersViewModel::class)
    fun topScorersViewModel(actorsListViewModel: TopScorersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubDetailsViewModel::class)
    fun clubDetailsViewModel(actorsListViewModel: ClubDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubMatchesViewModel::class)
    fun clubMatchesViewModel(actorsListViewModel: ClubMatchesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ClubStatisticsViewModel::class)
    fun clubStatisticsViewModel(actorsListViewModel: ClubStatisticsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MatchDetailsHomeViewModel::class)
    fun matchDetailsHomeViewModel(actorsListViewModel: MatchDetailsHomeViewModel): ViewModel
}
