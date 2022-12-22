package com.merio.footballManager.features.leaguehome.topscorers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.databinding.CellForTopScorersBinding
import com.merio.footballManager.domain.data.network.models.TopScorer

class TopScorersAdapter() : RecyclerView.Adapter<TopScorersAdapter.TopScorersViewHolder>() {

    class TopScorersViewHolder(val binding: CellForTopScorersBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val topScorerList: MutableList<TopScorer> = mutableListOf()

    fun setData(table: List<TopScorer>) {
        topScorerList.clear()
        topScorerList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopScorersViewHolder {
        val binding = CellForTopScorersBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TopScorersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopScorersViewHolder, position: Int) {
        val currentItem = topScorerList[position]
        with(holder.binding) {
            playerPositionInTheList.text = currentItem.pos.toString()
            namePlayer.text = currentItem.player.player_name
            nameClub.text = currentItem.team.team_name
            totalMatches.text = currentItem.matches_played.toString()
            totalNumberOfGoals.text = currentItem.goals.overall.toString()
        }
    }

    override fun getItemCount() = topScorerList.size
}
