package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.databinding.CellForMatchesBinding
import com.merio.footballManager.domain.data.network.models.Matches
import com.squareup.picasso.Picasso

class ClubMatchesAdapter(
    private val itemClicks: (Int) -> Unit
) : RecyclerView.Adapter<ClubMatchesAdapter.ClubMatchesViewHolder>() {

    class ClubMatchesViewHolder(val binding: CellForMatchesBinding) : RecyclerView.ViewHolder(binding.root)

    private val matchesList: MutableList<Matches> = mutableListOf()

    fun setData(match: List<Matches>) {
        matchesList.clear()
        matchesList.addAll(match)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMatchesViewHolder {
        val binding = CellForMatchesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubMatchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubMatchesViewHolder, position: Int) {
        with(holder.binding) {
            val currentItem = matchesList[position]
            textViewFirstTeam.text = currentItem.home_team.name
            textViewSecondTeam.text = currentItem.away_team.name
            textViewScore.text = currentItem.stats.ft_score
            textViewDate.text = currentItem.match_start

            Picasso.get()
                .load(currentItem.home_team.logo)
                .into(imageViewFirstTeam)
            Picasso.get()
                .load(currentItem.away_team.logo)
                .into(imageViewSecondTeam)

            holder.itemView.setOnClickListener {
                itemClicks(currentItem.match_id)
            }
        }
    }

    override fun getItemCount() = matchesList.size
}
