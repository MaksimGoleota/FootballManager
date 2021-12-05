package com.merio.footballManager.features.leaguehome.leagueteams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.one_cell_club.view.*

class LeagueTeamsAdapter(
    private val itemClicks: (Int) -> Unit
): RecyclerView.Adapter<LeagueTeamsAdapter.TeamsViewHolder>() {

    class TeamsViewHolder(View: View) : RecyclerView.ViewHolder(View) {

        val textItem: TextView = itemView.nameClub
        val logoClub: ImageView = itemView.logoClub

    }

    private val teamsList: MutableList<Teams> = mutableListOf()

    fun setData(teams: List<Teams>) {
        teamsList.clear()
        teamsList.addAll(teams)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.one_cell_club,
            parent, false
        )
        return TeamsViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {

        val currentItem = teamsList[position]
        holder.textItem.text = currentItem.name

        Picasso.get()
            .load(currentItem.logo)
            .into(holder.logoClub)

        holder.itemView.setOnClickListener {
            itemClicks(currentItem.team_id)
        }
    }

    override fun getItemCount() = teamsList.size

}
