package com.merio.footballManager.features.leaguehome.topscorers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.TopScorers
import kotlinx.android.synthetic.main.cell_for_top_scorers.view.*

class TopScorersAdapter : RecyclerView.Adapter<TopScorersAdapter.TopScorersViewHolder>() {

    class TopScorersViewHolder(View: View) : RecyclerView.ViewHolder(View) {
        val playerPositionInTheList: TextView = itemView.playerPositionInTheList
        val namePlayer: TextView = itemView.namePlayer
        val nameClub: TextView = itemView.nameClub
        val totalMatches: TextView = itemView.totalMatches
        val homeGoals: TextView = itemView.homeGoals
        val awayGoals: TextView = itemView.awayGoals
        val totalNumberOfGoals: TextView = itemView.totalNumberOfGoals
    }

    private val topScorersList: MutableList<TopScorers> = mutableListOf()

    fun setData(table: List<TopScorers>) {
        topScorersList.clear()
        topScorersList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopScorersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cell_for_top_scorers,
            parent, false
        )
        return TopScorersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopScorersViewHolder, position: Int) {
        val currentItem = topScorersList[position]
        holder.playerPositionInTheList.text = currentItem.pos.toString()
        holder.namePlayer.text = currentItem.player.player_name
        holder.nameClub.text = currentItem.team.team_name
        holder.totalMatches.text = currentItem.matches_played.toString()
        holder.homeGoals.text = currentItem.goals.home.toString()
        holder.awayGoals.text = currentItem.goals.away.toString()
        holder.totalNumberOfGoals.text = currentItem.goals.overall.toString()
    }

    override fun getItemCount() = topScorersList.size
}
