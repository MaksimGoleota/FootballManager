package com.merio.footballManager.features.clubdetailshome

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import kotlinx.android.synthetic.main.fragment_club_details_home.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.merio.footballManager.features.leaguehome.NameTabs

class ClubDetailsHomeFragment : Fragment() {

    private val args: ClubDetailsHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_details_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (args.countryId) {
            ENGLAND_ID -> {
                logo.setImageResource(R.drawable.premierleague)
                leagueName.text = getString(R.string.premier_league)
            }
            SPAIN_ID -> {
                logo.setImageResource(R.drawable.laliga)
                leagueName.text = getString(R.string.laliga)
            }
        }

        viewPager.adapter = ClubDetailsTabsAdapter(requireActivity(), args.countryId, args.teamId)

        val tabsArray = arrayOf(
            NameTabs.DETAILS.value,
            NameTabs.MATCHES.value,
            NameTabs.STATISTICS.value
        )

        TabLayoutMediator(tabLayoutClubDetails, viewPager) { tab, position ->
            tab.text = tabsArray[position]
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_baseline_article_24)
                1 -> tab.setIcon(R.drawable.outline_event_note_24)
                2 -> tab.setIcon(R.drawable.ic_baseline_leaderboard_24)
            }
            tabLayoutClubDetails.getTabAt(0)?.icon
                ?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)
        }.attach()

        tabLayoutClubDetails.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
