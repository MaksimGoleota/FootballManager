package com.merio.footballManager.features.clubdetailshome.clubstatistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ClubStatisticsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubStatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_statistics, container, false)
}
