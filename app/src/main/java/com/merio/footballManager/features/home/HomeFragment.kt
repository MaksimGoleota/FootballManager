package com.merio.footballManager.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.merio.footballManager.databinding.FragmentHomeBinding
import com.merio.footballManager.domain.data.network.api.ENGLAND_ID
import com.merio.footballManager.domain.data.network.api.SPAIN_ID
import dagger.android.support.DaggerFragment

class HomeFragment : DaggerFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        premierLeagueImageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToLeagueHome()
            action.countryId = ENGLAND_ID
            findNavController().navigate(action)
        }

        laLigaImageButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToLeagueHome()
            action.countryId = SPAIN_ID
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
