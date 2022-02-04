package com.merio.footballManager.features.leaguehome

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.merio.footballManager.R
import com.merio.footballManager.databinding.FragmentLeagueHomeBinding
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID

class LeagueHomeFragment : Fragment() {

    private val args: LeagueHomeFragmentArgs by navArgs()

    private var _binding: FragmentLeagueHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
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

        viewPager.adapter = LeagueHomeTabsAdapter(requireActivity(), args.countryId)

        val tabsArray = arrayOf(
            NameTabs.TABLE.value,
            NameTabs.TOP_SCORERS.value
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
            when (position) {
                0 -> tab.setIcon(R.drawable.outline_emoji_events_24)
                1 -> tab.setIcon(R.drawable.ic_top_scorers_24)
            }
            tabLayout.getTabAt(0)?.icon
                ?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(resources.getColor(R.color.red), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon?.setColorFilter(resources.getColor(R.color.black), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

enum class NameTabs(val value: String) {
    TABLE("Table"),
    TOP_SCORERS("Top scorers"),
    DETAILS("Details"),
    MATCHES("Matches"),
    STATISTICS("Statistics")
}




