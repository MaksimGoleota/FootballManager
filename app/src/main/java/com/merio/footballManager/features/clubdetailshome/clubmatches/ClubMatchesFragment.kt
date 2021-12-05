package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_club_matches.*
import kotlinx.android.synthetic.main.fragment_club_matches.progressBar
import kotlinx.android.synthetic.main.fragment_league_teams.*
import javax.inject.Inject

class ClubMatchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubMatchesViewModel
    private var seasonId: Int = -1
    private var teamId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seasonId = arguments?.getInt("seasonId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(ClubMatchesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_matches, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mViewModel.getMatches(seasonId, teamId)

        progressBar.visibility = View.VISIBLE

        val adapter = ClubMatchesAdapter()
        recyclerViewMatches.adapter = adapter
        recyclerViewMatches.layoutManager = LinearLayoutManager(context)
        recyclerViewMatches.setHasFixedSize(true)

        mViewModel.matchLiveData.observe(viewLifecycleOwner) { match ->
            adapter.setData(match)

            progressBar.visibility = View.INVISIBLE
        }
    }
}
