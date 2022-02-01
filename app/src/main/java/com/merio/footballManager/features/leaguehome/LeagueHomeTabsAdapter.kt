package com.merio.footballManager.features.leaguehome

import android.os.Bundle
import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.merio.footballManager.features.leaguehome.table.LeagueTableFragment
import com.merio.footballManager.features.leaguehome.topscorers.TopScorersFragment

class LeagueHomeTabsAdapter(activity: FragmentActivity, private val countryId: Int) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putInt("countryId", countryId)
        }
        return when (position) {
            0 -> {
                LeagueTableFragment().apply {
                    this.arguments = bundle
                }
            }
            else -> {
                TopScorersFragment().apply {
                    this.arguments = bundle
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
