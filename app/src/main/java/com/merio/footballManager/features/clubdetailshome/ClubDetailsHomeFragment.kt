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

class ClubDetailsHomeFragment: Fragment() {

    private val args: ClubDetailsHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_details_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when(args.countryId) {
            ENGLAND_ID -> {
                logoClubDetails.setImageResource(R.drawable.premierleague)
                leagueNameClubDetails.text = getString(R.string.premier_league)
            }
            SPAIN_ID -> {
                logoClubDetails.setImageResource(R.drawable.laliga)
                leagueNameClubDetails.text = getString(R.string.laliga)
            }
        }

        viewPagerTeams.adapter = ClubDetailsTabsAdapter(requireActivity().supportFragmentManager, args.teamId, args.countryId)
        tabLayoutClubDetails.setupWithViewPager(viewPagerTeams)

        tabLayoutClubDetails.getTabAt(0)?.setIcon(R.drawable.ic_baseline_article_24)
        tabLayoutClubDetails.getTabAt(1)?.setIcon(R.drawable.outline_event_note_24)
        tabLayoutClubDetails.getTabAt(2)?.setIcon(R.drawable.ic_baseline_leaderboard_24)

        tabLayoutClubDetails.getTabAt(0)?.icon
            ?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)

        tabLayoutClubDetails.getTabAt(0)?.text = "Details"
        tabLayoutClubDetails.getTabAt(1)?.text = "Matches"
        tabLayoutClubDetails.getTabAt(2)?.text = "Statistics"

        tabLayoutClubDetails.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
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
