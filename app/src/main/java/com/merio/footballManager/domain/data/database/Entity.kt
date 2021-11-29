package com.merio.footballManager.domain.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
class Entity (
    @PrimaryKey(autoGenerate = false)
    val team_id: Int,
    val name: String,
    val short_code: String,
    val logo: String?,
    val country_id: Int,
    val name_country: String,
    val country_code: String?,
    val continent: String
)
