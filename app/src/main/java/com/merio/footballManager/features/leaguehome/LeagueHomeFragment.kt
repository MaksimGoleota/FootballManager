package com.merio.footballManager.features.leaguehome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import kotlinx.android.synthetic.main.fragment_league_home.*


class LeagueHomeFragment : Fragment() {

    val args: LeagueHomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_league_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when(args.leagueId) {
            ENGLAND_ID -> {
                logo.setImageResource(R.drawable.premierleague)
                leagueName.text = getString(R.string.premier_league)
            }
            SPAIN_ID -> {
                logo.setImageResource(R.drawable.laliga)
                leagueName.text = getString(R.string.laliga)
            }
        }

        viewPager.adapter = LeagueHomeTabsAdapter(requireActivity().supportFragmentManager, args.leagueId)
        tabLayout.setupWithViewPager(viewPager)
    }
}

