package com.merio.footballManager.domain.dagger.modules

import com.merio.footballManager.features.country.CountryFragment
import com.merio.footballManager.features.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCountryFragment(): CountryFragment


}
