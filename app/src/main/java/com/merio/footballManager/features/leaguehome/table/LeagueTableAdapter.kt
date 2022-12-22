package com.merio.footballManager.features.leaguehome.table

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.databinding.CellForTableBinding
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.squareup.picasso.Picasso

class LeagueTableAdapter(
    private val itemClicks: (Int) -> Unit
) : RecyclerView.Adapter<LeagueTableAdapter.TableViewHolder>() {

    class TableViewHolder(val binding: CellForTableBinding) : RecyclerView.ViewHolder(binding.root)

    private val tableList: MutableList<TableTeam> = mutableListOf()

    fun setData(table: List<TableTeam>) {
        tableList.clear()
        tableList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = CellForTableBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        with(holder) {
            val currentItem = tableList[position]
            binding.positionClubInTable.text = currentItem.position.toString()
            binding.nameClubForTable.text = currentItem.team.name
            binding.pointsClubInTable.text = currentItem.points.toString()

            Picasso.get()
                .load(currentItem.team.logo)
                .into(binding.logoClubForTable)

            when (currentItem.result) {
                Status.CHAMPIONS_LEAGUE.value -> binding.resultLabel.setBackgroundResource(R.color.purple_700)
                Status.EUROPA_LEAGUE.value -> binding.resultLabel.setBackgroundResource(R.color.orange)
                Status.CONFERENCE_LEAGUE_QUALIFICATION.value -> binding.resultLabel.setBackgroundResource(
                    android.R.color.holo_green_dark
                )
                Status.RELEGATION.value -> binding.resultLabel.setBackgroundResource(R.color.red)
                else -> binding.resultLabel.setBackgroundResource(R.color.white)
            }

            holder.itemView.setOnClickListener {
                itemClicks(currentItem.team.team_id)
            }
        }

    }

    override fun getItemCount() = tableList.size
}

enum class Status(val value: String) {
    CHAMPIONS_LEAGUE("Champions League"),
    EUROPA_LEAGUE("UEFA Europa League"),
    CONFERENCE_LEAGUE_QUALIFICATION("Conference League Qualification"),
    RELEGATION("Relegation"),
    WITHOUT_CHANGES("Without changes")
}
