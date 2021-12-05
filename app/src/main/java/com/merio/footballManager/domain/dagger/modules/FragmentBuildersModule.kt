package com.merio.footballManager.domain.dagger.modules

import com.merio.footballManager.features.clubdetailshome.clubdetails.ClubDetailsFragment
import com.merio.footballManager.features.clubdetailshome.clubmatches.ClubMatchesFragment
import com.merio.footballManager.features.clubdetailshome.clubstatistics.ClubStatisticsFragment
import com.merio.footballManager.features.home.HomeFragment
import com.merio.footballManager.features.leaguehome.leagueteams.LeagueTeamsFragment
import com.merio.footballManager.features.leaguehome.table.LeagueTableFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeLeagueTeamsFragment(): LeagueTeamsFragment

    @ContributesAndroidInjector
    abstract fun contributeLeagueTableFragment(): LeagueTableFragment

    @ContributesAndroidInjector
    abstract fun contributeClubDetailsFragment(): ClubDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeClubMatchesFragment(): ClubMatchesFragment

    @ContributesAndroidInjector
    abstract fun contributeClubStatisticsFragment(): ClubStatisticsFragment


}
