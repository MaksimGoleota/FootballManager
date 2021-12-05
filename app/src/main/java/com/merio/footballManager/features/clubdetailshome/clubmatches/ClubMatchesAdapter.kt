package com.merio.footballManager.features.clubdetailshome.clubmatches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.Matches
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.one_cell_for_matches.view.*

class ClubMatchesAdapter: RecyclerView.Adapter<ClubMatchesAdapter.ClubMatchesViewHolder>() {

    class ClubMatchesViewHolder(View: View) : RecyclerView.ViewHolder(View) {

        val imageViewFirstTeam: ImageView = itemView.imageViewFirstTeam
        val imageViewSecondTeam: ImageView = itemView.imageViewSecondTeam
        val textViewFirstTeam: TextView = itemView.textViewFirstTeam
        val textViewSecondTeam: TextView = itemView.textViewSecondTeam
        val textViewScore: TextView = itemView.textViewScore
        val textViewDate: TextView = itemView.textViewDate
    }

    private val matchesList: MutableList<Matches> = mutableListOf()

    fun setData(match: List<Matches>) {
        matchesList.clear()
        matchesList.addAll(match)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMatchesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.one_cell_for_matches,
            parent, false
        )
        return ClubMatchesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClubMatchesViewHolder, position: Int) {

        val currentItem = matchesList[position]

        holder.textViewFirstTeam.text = currentItem.home_team.name
        holder.textViewSecondTeam.text = currentItem.away_team.name
        holder.textViewScore.text = currentItem.stats.ft_score
        holder.textViewDate.text = currentItem.match_start

        Picasso.get()
            .load(currentItem.home_team.logo)
            .into(holder.imageViewFirstTeam)
        Picasso.get()
            .load(currentItem.away_team.logo)
            .into(holder.imageViewSecondTeam)
    }

    override fun getItemCount() = matchesList.size
}
