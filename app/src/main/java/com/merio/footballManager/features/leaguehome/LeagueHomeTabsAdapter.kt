package com.merio.footballManager.features.leaguehome

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.merio.footballManager.features.leagueteams.LeagueTeamsFragment
import com.merio.footballManager.features.table.LeagueTableFragment

class LeagueHomeTabsAdapter(
    fm: FragmentManager,
    private val countryId: Int
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        val bundle = Bundle().apply {
            putInt("countryId", countryId)
        }
        return when (position) {
            0 -> {
                LeagueTeamsFragment().apply {
                    this.arguments = bundle
                }
            }
            else -> {
                LeagueTableFragment().apply {
                    this.arguments = bundle
                }
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) {
            "Teams"
        } else {
            "Table"
        }
    }
}
