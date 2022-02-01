package com.merio.footballManager.domain.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merio.footballManager.domain.data.network.models.Teams

@Database(entities = [Teams::class],version = 1,exportSchema = false)
abstract class TeamsDatabase : RoomDatabase() {

    abstract fun dao(): TeamDao

    companion object {
        @Volatile
        var INSTANCE: TeamsDatabase? = null
    }

}
