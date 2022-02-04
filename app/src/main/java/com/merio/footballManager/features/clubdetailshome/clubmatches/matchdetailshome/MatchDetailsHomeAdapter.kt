package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.databinding.CellForStatisticsBinding

class MatchDetailsHomeAdapter :
    RecyclerView.Adapter<MatchDetailsHomeAdapter.ClubMatchesViewHolder>() {

    class ClubMatchesViewHolder(val binding: CellForStatisticsBinding) : RecyclerView.ViewHolder(binding.root)
    private val statistics: MutableList<StatisticsItem> = mutableListOf()

    fun setData(matchStatistics: List<StatisticsItem>) {
        statistics.clear()
        statistics.addAll(matchStatistics)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMatchesViewHolder {
        val binding = CellForStatisticsBinding
            .inflate( LayoutInflater.from(parent.context), parent, false)
        return ClubMatchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubMatchesViewHolder, position: Int) {
        with(holder.binding){
            val currentItem = statistics[position]
            teamStats.setText(currentItem.statisticsLabel)
            statisticsFirstTeam.text = currentItem.homeTeamValue
            statisticsSecondTeam.text = currentItem.awayTeamValue
        }
    }

    override fun getItemCount() = statistics.size
}
