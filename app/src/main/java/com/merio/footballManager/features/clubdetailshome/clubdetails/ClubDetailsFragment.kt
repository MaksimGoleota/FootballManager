package com.merio.footballManager.features.clubdetailshome.clubdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_club_details.*
import javax.inject.Inject


class ClubDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubDetailsViewModel
    private var teamId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}
