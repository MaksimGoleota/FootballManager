package com.merio.footballManager.domain.data.network.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TeamsResponse(
    val query: TeamsQuery,
    val data: List<TeamDTO>
)

data class TeamsQuery(
    val country_id: String,
    val apikey: String
)

@Entity(tableName = "teams")
class Teams(
    @PrimaryKey(autoGenerate = false)
    val team_id: Int,
    val name: String,
    val short_code: String,
    var logo: String?,
    val countryId: Int
) {
    fun getFormattedProfilePath(): String? {
        logo = logo?.replace("\\/", "/", ignoreCase = false)
        return logo
    }
}

class TeamDTO(
    val team_id: Int,
    val name: String,
    val short_code: String,
    var logo: String?,
    val country: Country
)

