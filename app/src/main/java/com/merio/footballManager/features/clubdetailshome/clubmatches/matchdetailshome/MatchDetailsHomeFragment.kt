package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.databinding.FragmentMatchDetailsHomeBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MatchDetailsHomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var matchId: Int = -1

    private lateinit var mViewModel: MatchDetailsHomeViewModel

    private var _binding: FragmentMatchDetailsHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        matchId = arguments?.getInt(MATCH_ID) ?: -1
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(MatchDetailsHomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailsHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        progressBar.visibility = View.VISIBLE

        mViewModel.getMatchInfo(matchId)

        val adapter = MatchDetailsHomeAdapter()
        recyclerViewStatistics.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        mViewModel.statisticsLiveData.observe(viewLifecycleOwner) { matchStatistics ->
            adapter.setData(matchStatistics)
            progressBar.visibility = View.GONE
            linearLayout.visibility = View.VISIBLE
        }

        dataForCollapsingToolbar()
        matchStatisticHeader()
    }

    private fun dataForCollapsingToolbar() = with(binding) {
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

        }
    }

    private fun matchStatisticHeader() = with(binding) {
        mViewModel.matchInfoLiveData.observe(viewLifecycleOwner) { matchId ->
            Picasso.get()
                .load(matchId.home_team.getFormattedProfilePath())
                .into(logoFirstTeamStatistic)
            Picasso.get()
                .load(matchId.away_team.getFormattedProfilePath())
                .into(logoSecondTeamStatistic)
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

