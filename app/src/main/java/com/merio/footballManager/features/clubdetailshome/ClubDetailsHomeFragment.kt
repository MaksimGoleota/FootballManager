package com.merio.footballManager.features.clubdetailshome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.merio.footballManager.databinding.FragmentClubDetailsHomeBinding
import com.merio.footballManager.features.clubdetailshome.ClubDetailsTabsAdapter.Companion.tabNames

class ClubDetailsHomeFragment : Fragment() {

    private val args: ClubDetailsHomeFragmentArgs by navArgs()

    private var _binding: FragmentClubDetailsHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubDetailsHomeBinding.inflate(inflater, container, false)
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

        viewPager.adapter = ClubDetailsTabsAdapter(requireActivity(), args.countryId, args.teamId)

        TabLayoutMediator(tabLayoutClubDetails, viewPager) { tab, position ->
            tab.text = tabNames[position]
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_baseline_article_24)
                1 -> tab.setIcon(R.drawable.outline_event_note_24)
            }
            tabLayoutClubDetails
                .getTabAt(0)
                ?.icon
                ?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(requireContext(), R.color.red), BlendModeCompat.SRC_ATOP
            )
        }.attach()

        tabLayoutClubDetails.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon
                    ?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    ContextCompat.getColor(requireContext(), R.color.red), BlendModeCompat.SRC_ATOP
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon
                    ?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    ContextCompat.getColor(requireContext(), R.color.black),
                    BlendModeCompat.SRC_ATOP
                )
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
