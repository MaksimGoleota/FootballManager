package com.merio.footballManager.features.leaguehome.topscorers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.databinding.FragmentTopScorersBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TopScorersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: TopScorersViewModel
    private var countryId: Int = -1
    private var teamId: Int = -1

    private var _binding: FragmentTopScorersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryId = arguments?.getInt("countryId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(TopScorersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopScorersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        when (countryId) {
            ENGLAND_ID -> mViewModel.getTopScorers(PREMIER_LEAGUE_SEASON_ID)
            SPAIN_ID -> mViewModel.getTopScorers(LALIGA_SEASON_ID)
        }

        progressBarTopScorers.visibility = View.VISIBLE

        val adapter = TopScorersAdapter()
        recyclerViewTopScorers.adapter = adapter
        recyclerViewTopScorers.layoutManager = LinearLayoutManager(context)
        recyclerViewTopScorers.setHasFixedSize(true)

        mViewModel.topScorersLiveData.observe(viewLifecycleOwner) { table ->
            adapter.setData(table)
            progressBarTopScorers.visibility = View.INVISIBLE

            positionHeader.visibility = View.VISIBLE
            playersHeader.visibility = View.VISIBLE
            totalGoalsHeader.visibility = View.VISIBLE
            totalMatchesHeader.visibility = View.VISIBLE
            splitLine.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
