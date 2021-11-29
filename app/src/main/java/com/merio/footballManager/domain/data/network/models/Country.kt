package com.merio.footballManager.domain.data.network.models

data class CountryResponse(
    val query: CountryQuery,
    val data: List<Country>
)

data class CountryQuery(
    val apikey: String
)

data class Country(
    val country_id: Int,
    val name_country: String,
    val country_code: String?,
    val continent: String
)
