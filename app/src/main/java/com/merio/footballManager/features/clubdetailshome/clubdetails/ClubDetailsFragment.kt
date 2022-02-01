package com.merio.footballManager.features.clubdetailshome.clubdetails

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
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_club_details.*
import kotlinx.android.synthetic.main.fragment_club_details.progressBar
import javax.inject.Inject


class ClubDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubDetailsViewModel
    private var countryId: Int = -1
    private var teamId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryId = arguments?.getInt("countryId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(ClubDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_details, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel.getDetails(teamId)

        mViewModel.leagueTeamsLiveData.observe(viewLifecycleOwner) { team ->
            Picasso.get()
                .load(team.getFormattedProfilePath())
                .into(imageClubDetails)
            textViewClubDetails.text = team.name

        }
        when (countryId) {
            ENGLAND_ID -> mViewModel.getTable(PREMIER_LEAGUE_SEASON_ID)
            SPAIN_ID -> mViewModel.getTable(LALIGA_SEASON_ID)
        }

        progressBar.visibility = View.VISIBLE

        val adapter = ClubDetailsAdapter(teamId)
        recyclerViewClubDetails.adapter = adapter
        recyclerViewClubDetails.layoutManager = LinearLayoutManager(context)
        recyclerViewClubDetails.setHasFixedSize(true)

        mViewModel.leagueTableLiveData.observe(viewLifecycleOwner) { table ->
            adapter.setData(table)
            progressBar.visibility = View.INVISIBLE
        }
    }
}
