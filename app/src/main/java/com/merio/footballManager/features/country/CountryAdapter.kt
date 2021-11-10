package com.merio.footballManager.features.country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.Country
import kotlinx.android.synthetic.main.one_cell_country.view.*

class CountryAdapter(
    private val actionListener: CountryFragment
    ): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(View: View) : RecyclerView.ViewHolder(View) {

        val textItem: TextView = itemView.text_view
    }

    private val countryList: MutableList<Country> = mutableListOf()

    fun setData(countries: List<Country>) {
        countryList.clear()
        countryList.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.one_cell_country,
            parent, false
        )
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        val currentItem = countryList[position]
        holder.textItem.text = currentItem.name

    }

    override fun getItemCount() = countryList.size
}

