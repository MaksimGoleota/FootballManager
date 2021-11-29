package com.merio.footballManager.domain.dagger

import android.app.Application
import com.merio.footballManager.FMApplication
import com.merio.footballManager.domain.dagger.modules.ActivityBuildersModule
import com.merio.footballManager.domain.dagger.modules.ViewModelModule
import com.merio.footballManager.domain.data.dagger.ApiModule
import com.merio.footballManager.domain.data.dagger.DatabaseModule
import com.merio.footballManager.domain.data.dagger.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class,
        ApiModule::class,
        DatabaseModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent: AndroidInjector<FMApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
