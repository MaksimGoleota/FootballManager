package com.merio.footballManager.domain.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.merio.footballManager.domain.data.network.models.Teams
import io.reactivex.Single

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeams(teams: List<Teams>)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Single<List<Teams>>

    @Query("SELECT * FROM teams WHERE countryId = :countryId")
    fun getCountryTeams(countryId: Int): Single<List<Teams>>
}
