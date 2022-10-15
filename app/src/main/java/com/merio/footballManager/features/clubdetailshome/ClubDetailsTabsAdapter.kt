package com.merio.footballManager.features.clubdetailshome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.features.clubdetailshome.clubdetails.ClubDetailsFragment
import com.merio.footballManager.features.clubdetailshome.clubmatches.ClubMatchesFragment

class ClubDetailsTabsAdapter(
    activity: FragmentActivity,
    private val countryId: Int,
    private val teamId: Int
) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle().apply {
                    putInt(TEAM_ID, teamId)
                    putInt(COUNTRY_ID, countryId)
                }
                ClubDetailsFragment().apply {
                    this.arguments = bundle
                }
            }
            else -> {
                val bundle = Bundle().apply {
                    putInt(
                        SEASON_ID,
                        if (countryId == ENGLAND_ID) PREMIER_LEAGUE_SEASON_ID else LALIGA_SEASON_ID
                    )
                    putInt(TEAM_ID, teamId)
                }
                ClubMatchesFragment().apply {
                    this.arguments = bundle
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return tabNames.size
    }

    companion object {
        const val COUNTRY_ID = "countryId"
        const val TEAM_ID = "teamId"
        const val SEASON_ID = "seasonId"

        val tabNames = arrayOf("Details", "Matches", "Statistics")
    }
}
