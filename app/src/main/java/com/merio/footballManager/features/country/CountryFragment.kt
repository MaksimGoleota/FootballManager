package com.merio.footballManager.features.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.merio.footballManager.MainActivity
import com.merio.footballManager.R
import com.merio.footballManager.domain.dagger.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_country.*
import javax.inject.Inject

class CountryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mViewModel: CountryFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(CountryFragmentViewModel::class.java)
        mViewModel.getAllCountries()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_country, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).hideBottomNavigation()

        val adapter = CountryAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        mViewModel.countryLiveData.observe(viewLifecycleOwner) { countries ->
            adapter.setData(countries)
        }
    }
}
