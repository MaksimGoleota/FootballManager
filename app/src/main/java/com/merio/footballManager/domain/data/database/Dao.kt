package com.merio.footballManager.domain.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.merio.footballManager.domain.data.network.models.Teams
import io.reactivex.Single

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeams(teams: List<Teams>)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Single<List<Teams>>

}
