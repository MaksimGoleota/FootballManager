package com.merio.footballManager.features.leaguehome.topscorers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.databinding.CellForTopScorersBinding
import com.merio.footballManager.domain.data.network.models.TopScorers

class TopScorersAdapter : RecyclerView.Adapter<TopScorersAdapter.TopScorersViewHolder>() {

    class TopScorersViewHolder(val binding: CellForTopScorersBinding) : RecyclerView.ViewHolder(binding.root)

    private val topScorersList: MutableList<TopScorers> = mutableListOf()

    fun setData(table: List<TopScorers>) {
        topScorersList.clear()
        topScorersList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopScorersViewHolder {
        val binding = CellForTopScorersBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TopScorersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopScorersViewHolder, position: Int) {
        with(holder) {
            val currentItem = topScorersList[position]
            binding.playerPositionInTheList.text = currentItem.pos.toString()
            binding.namePlayer.text = currentItem.player.player_name
            binding.nameClub.text = currentItem.team.team_name
            binding.totalMatches.text = currentItem.matches_played.toString()
            binding.totalNumberOfGoals.text = currentItem.goals.overall.toString()
        }
    }

    override fun getItemCount() = topScorersList.size
}
