package com.merio.footballManager.features.leaguehome

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import kotlinx.android.synthetic.main.fragment_league_home.*


class LeagueHomeFragment : Fragment() {

    private val args: LeagueHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_league_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when(args.countryId) {
            ENGLAND_ID -> {
                logo.setImageResource(R.drawable.premierleague)
                leagueName.text = getString(R.string.premier_league)
            }
            SPAIN_ID -> {
                logo.setImageResource(R.drawable.laliga)
                leagueName.text = getString(R.string.laliga)
            }
        }

        viewPager.adapter = LeagueHomeTabsAdapter(requireActivity().supportFragmentManager, args.countryId)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.setIcon(R.drawable.outline_ballot_24)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.outline_emoji_events_24)

        tabLayout.getTabAt(0)?.icon
            ?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)

        tabLayout.getTabAt(0)?.text = "Teams"
        tabLayout.getTabAt(1)?.text = "Table"

        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
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

