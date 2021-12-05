package com.merio.footballManager.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        premierLeagueImageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToLeagueHome()
            action.countryId = ENGLAND_ID
            findNavController().navigate(action)
        }

        laLigaImageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToLeagueHome()
            action.countryId = SPAIN_ID
            findNavController().navigate(action)        }
    }
}
