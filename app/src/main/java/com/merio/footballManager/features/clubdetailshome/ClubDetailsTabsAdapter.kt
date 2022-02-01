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
import com.merio.footballManager.features.clubdetailshome.clubstatistics.ClubStatisticsFragment

class ClubDetailsTabsAdapter(
    activity: FragmentActivity,
    private val countryId: Int,
    private val teamId: Int
) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle().apply {
                    putInt("teamId", teamId)
                    putInt("countryId", countryId)
                }
                ClubDetailsFragment().apply {
                    this.arguments = bundle
                }
            }
            1 -> {
                val bundle = Bundle().apply {
                    putInt(
                        "seasonId",
                        if (countryId == ENGLAND_ID) PREMIER_LEAGUE_SEASON_ID else LALIGA_SEASON_ID
                    )
                    putInt("teamId", teamId)
                }
                ClubMatchesFragment().apply {
                    this.arguments = bundle
                }
            }
            else -> {
                ClubStatisticsFragment().apply {
//                    this.arguments = bundle
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
