package com.merio.footballManager.domain.dagger.modules

import com.merio.footballManager.features.home.HomeFragment
import com.merio.footballManager.features.leagueteams.LeagueTeamsFragment
import com.merio.footballManager.features.table.LeagueTableFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributePremierLeagueHomeFragment(): LeagueTeamsFragment

    @ContributesAndroidInjector
    abstract fun contributePremierLeagueTableFragment(): LeagueTableFragment


}
