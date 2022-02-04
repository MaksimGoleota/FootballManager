package com.merio.footballManager.features.leaguehome.table

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.databinding.FragmentLeagueTableBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LeagueTableFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: LeagueTableViewModel
    private var countryId: Int = -1
    private var teamId: Int = -1

    private var _binding: FragmentLeagueTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryId = arguments?.getInt("countryId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(LeagueTableViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeagueTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        progressBar.visibility = View.VISIBLE

        when (countryId) {
            ENGLAND_ID -> mViewModel.getTable(PREMIER_LEAGUE_SEASON_ID)
            SPAIN_ID -> mViewModel.getTable(LALIGA_SEASON_ID)
        }

        val adapter = LeagueTableAdapter { teamId ->
            val bundle = Bundle().apply {
                putInt("countryId", countryId)
                putInt("teamId", teamId)
            }
            findNavController().navigate(R.id.clubDetailsHomeFragment, bundle)
        }
        recyclerViewTable.adapter = adapter
        recyclerViewTable.layoutManager = LinearLayoutManager(context)
        recyclerViewTable.setHasFixedSize(true)

        mViewModel.leagueTableLiveData.observe(viewLifecycleOwner) { table ->
            adapter.setData(table)
            progressBar.visibility = View.INVISIBLE

            pointsHeader.visibility = View.VISIBLE
            teamsHeader.visibility = View.VISIBLE
            positionHeader.visibility = View.VISIBLE
            splitLine.visibility = View.VISIBLE

        }
    }

    override fun onResume() {
        Log.d("Network error", "$countryId")
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
