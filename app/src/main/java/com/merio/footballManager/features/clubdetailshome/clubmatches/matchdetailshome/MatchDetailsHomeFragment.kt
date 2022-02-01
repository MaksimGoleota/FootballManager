package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_match_details_home.*
import kotlinx.android.synthetic.main.fragment_match_details_home.progressBar
import javax.inject.Inject

class MatchDetailsHomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var matchId: Int = -1

    private lateinit var mViewModel: MatchDetailsHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchId = arguments?.getInt("matchId") ?: -1
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(MatchDetailsHomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_match_details_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar.visibility = View.VISIBLE

        mViewModel.getMatchInfo(matchId)

        val adapter = MatchDetailsHomeAdapter()
        recyclerViewStatistics.adapter = adapter
        recyclerViewStatistics.layoutManager = LinearLayoutManager(context)
        recyclerViewStatistics.setHasFixedSize(true)

        mViewModel.statisticsLiveData.observe(viewLifecycleOwner) { matchStatistics ->
            adapter.setData(matchStatistics)
            progressBar.visibility = View.INVISIBLE
            linearLayout.visibility = View.VISIBLE
        }

        dataForCollapsingToolbar()
        dataForStatisticsMatches()
    }

    private fun dataForCollapsingToolbar() {
        mViewModel.matchInfoLiveData.observe(viewLifecycleOwner) { matchId ->
            Picasso.get()
                .load(matchId.home_team.getFormattedProfilePath())
                .into(imageViewFirstTeam)
            Picasso.get()
                .load(matchId.away_team.getFormattedProfilePath())
                .into(imageViewSecondTeam)

            nameFirstTeam.text = matchId.home_team.name
            nameSecondTeam.text = matchId.away_team.name
            data.text = matchId.match_start
            score.text = matchId.stats.ft_score
            status.text = matchId.status
            venue.text = matchId.venue.name

            btnCalendar.setOnClickListener {
                findNavController().navigate(R.id.action_matchDetailsHomeFragment_to_clubMatchesFragment)
            }
            btnTable.setOnClickListener {
                findNavController().navigate(R.id.action_matchDetailsHomeFragment_to_leagueTableFragment)
            }
        }
    }

    private fun dataForStatisticsMatches() {

        mViewModel.matchInfoLiveData.observe(viewLifecycleOwner) { matchId ->
            Picasso.get()
                .load(matchId.home_team.getFormattedProfilePath())
                .into(logoFirstTeamStatistic)
            Picasso.get()
                .load(matchId.away_team.getFormattedProfilePath())
                .into(logoSecondTeamStatistic)
        }

    }
}

