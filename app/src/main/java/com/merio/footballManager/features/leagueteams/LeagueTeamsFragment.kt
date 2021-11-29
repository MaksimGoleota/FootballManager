package com.merio.footballManager.features.leagueteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_league_teams.*
import javax.inject.Inject

class LeagueTeamsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: LeagueTeamsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(LeagueTeamsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(com.merio.footballManager.R.layout.fragment_league_teams, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.getTeams(arguments?.getInt("countryId") ?: -1)

        progressBar.visibility = View.VISIBLE


        val adapter = LeagueTeamsAdapter(this)

        recyclerViewPremierLeague.adapter = adapter
        recyclerViewPremierLeague.layoutManager = LinearLayoutManager(context)
        recyclerViewPremierLeague.setHasFixedSize(true)

        mViewModel.teamsLiveData.observe(viewLifecycleOwner) { teams ->
            adapter.setData(teams)
            progressBar.visibility = View.INVISIBLE
        }
    }
}
