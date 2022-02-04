package com.merio.footballManager.features.clubdetailshome.clubstatistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.merio.footballManager.databinding.FragmentClubStatisticsBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ClubStatisticsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubStatisticsViewModel

    private var _binding: FragmentClubStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
