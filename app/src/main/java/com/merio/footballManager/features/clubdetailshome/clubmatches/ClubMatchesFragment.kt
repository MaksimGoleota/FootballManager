package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.databinding.FragmentClubMatchesBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.features.clubdetailshome.ClubDetailsTabsAdapter.Companion.SEASON_ID
import com.merio.footballManager.features.clubdetailshome.ClubDetailsTabsAdapter.Companion.TEAM_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ClubMatchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubMatchesViewModel
    private var seasonId: Int = -1
    private var teamId: Int = -1

    private var _binding: FragmentClubMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seasonId = arguments?.getInt(SEASON_ID) ?: -1
        teamId = arguments?.getInt(TEAM_ID) ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(ClubMatchesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        mViewModel.getMatches(seasonId, teamId)

        progressBar.visibility = View.VISIBLE

        val adapter = ClubMatchesAdapter { matchId ->
            val bundle = Bundle().apply {
                putInt(MATCH_ID, matchId)
            }
            findNavController().navigate(R.id.matchDetailsHomeFragment, bundle)
        }
        recyclerViewMatches.adapter = adapter
        recyclerViewMatches.layoutManager = LinearLayoutManager(context)
        recyclerViewMatches.setHasFixedSize(true)

        mViewModel.matchLiveData.observe(viewLifecycleOwner) { match ->
            adapter.setData(match)

            progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MATCH_ID = "matchId"
    }
}
