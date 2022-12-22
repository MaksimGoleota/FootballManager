package com.merio.footballManager.domain.data.dagger

import android.app.Application
import androidx.room.Room
import com.merio.footballManager.domain.data.database.TeamsDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun getTeamsDatabase(application: Application): TeamsDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                application.applicationContext,
                TeamsDatabase::class.java,
                "teams_database"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}
