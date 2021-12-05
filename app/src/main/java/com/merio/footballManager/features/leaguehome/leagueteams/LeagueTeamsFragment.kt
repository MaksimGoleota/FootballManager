package com.merio.footballManager.features.leaguehome.leagueteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_league_teams.*
import javax.inject.Inject

class LeagueTeamsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: LeagueTeamsViewModel
    private var countryId: Int = -1
    private var teamId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryId = arguments?.getInt("countryId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(LeagueTeamsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_league_teams, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.getTeams(countryId)

        progressBar.visibility = View.VISIBLE

        val adapter = LeagueTeamsAdapter { teamId ->
            val bundle = Bundle().apply {
                putInt("countryId", countryId)
                putInt("teamId", teamId)
            }
            findNavController().navigate(R.id.clubDetailsHomeFragment, bundle)
        }

        recyclerViewTeams.adapter = adapter
        recyclerViewTeams.layoutManager = LinearLayoutManager(context)
        recyclerViewTeams.setHasFixedSize(true)

        mViewModel.teamsLiveData.observe(viewLifecycleOwner) { teams ->
            adapter.setData(teams)
            progressBar.visibility = View.INVISIBLE
        }
    }
}
