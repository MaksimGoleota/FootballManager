package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.R
import com.merio.footballManager.databinding.FragmentClubMatchesBinding
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ClubMatchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: ClubMatchesViewModel
    private var seasonId: Int = -1
    private var teamId: Int = -1
    private var matchId: Int = -1

    private var _binding: FragmentClubMatchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seasonId = arguments?.getInt("seasonId") ?: -1
        teamId = arguments?.getInt("teamId") ?: -1
        matchId = arguments?.getInt("matchId") ?: -1
        mViewModel = ViewModelProvider(this, viewModelFactory).get(ClubMatchesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

        mViewModel.getMatches(seasonId, teamId)

        progressBar.visibility = View.VISIBLE

        val adapter = ClubMatchesAdapter { matchId ->
            val bundle = Bundle().apply {
                putInt("matchId", matchId)
            }
            findNavController().navigate(R.id.matchDetailsHomeFragment, bundle)
        }
        recyclerViewMatches.adapter = adapter
        recyclerViewMatches.layoutManager = LinearLayoutManager(context)
        recyclerViewMatches.setHasFixedSize(true)

        mViewModel.matchLiveData.observe(viewLifecycleOwner) { match ->
            adapter.setData(match)

            progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
