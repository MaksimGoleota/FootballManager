package com.merio.footballManager.features.clubdetailshome.clubmatches.matchdetailshome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import kotlinx.android.synthetic.main.one_cell_for_statistics.view.*

class MatchDetailsHomeAdapter :
    RecyclerView.Adapter<MatchDetailsHomeAdapter.ClubMatchesViewHolder>() {

    class ClubMatchesViewHolder(View: View) : RecyclerView.ViewHolder(View) {
        val label: TextView = itemView.teamStats
        val statisticsFirstTeam: TextView = itemView.statisticsFirstTeam
        val statisticsSecondTeam: TextView = itemView.statisticsSecondTeam
    }

    private val statistics: MutableList<StatisticsItem> = mutableListOf()

    fun setData(matchStatistics: List<StatisticsItem>) {
        statistics.clear()
        statistics.addAll(matchStatistics)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMatchesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.one_cell_for_statistics,
            parent, false
        )
        return ClubMatchesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClubMatchesViewHolder, position: Int) {

        val currentItem = statistics[position]
        holder.label.setText(currentItem.statisticsLabel)
        holder.statisticsFirstTeam.text = currentItem.homeTeamValue
        holder.statisticsSecondTeam.text = currentItem.awayTeamValue
    }

    override fun getItemCount() = statistics.size
}
