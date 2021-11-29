package com.merio.footballManager.features.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.LALIGA_SEASON_ID
import com.merio.footballManager.domain.data.network.api.PREMIER_LEAGUE_SEASON_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_league_table.*
import javax.inject.Inject

class LeagueTableFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: LeagueTableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(LeagueTableViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_league_table, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when(arguments?.getInt("countryId") ?: -1) {
            ENGLAND_ID -> mViewModel.getTable(PREMIER_LEAGUE_SEASON_ID)
            SPAIN_ID -> mViewModel.getTable(LALIGA_SEASON_ID)
        }

        progressBar.visibility = View.VISIBLE

        val adapter = LeagueTableAdapter()

        recyclerViewPremierLeagueTable.adapter = adapter
        recyclerViewPremierLeagueTable.layoutManager = LinearLayoutManager(context)
        recyclerViewPremierLeagueTable.setHasFixedSize(true)

        mViewModel.premierLeagueTableLiveData.observe(viewLifecycleOwner) { table ->
            adapter.setData(table)
            progressBar.visibility = View.INVISIBLE
        }
    }
}
