package com.merio.footballManager.features.clubdetailshome.clubdetails

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.databinding.CellForDetailsTableBinding
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.merio.footballManager.features.leaguehome.table.Status
import com.squareup.picasso.Picasso

class ClubDetailsAdapter(private val currentTeamId: Int) :
    RecyclerView.Adapter<ClubDetailsAdapter.TableViewHolder>() {

    class TableViewHolder(val binding: CellForDetailsTableBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val tableList: MutableList<TableTeam> = mutableListOf()

    fun setData(table: List<TableTeam>) {
        tableList.clear()
        tableList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = CellForDetailsTableBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val currentItem = tableList[position]
        with(holder.binding) {
            positionClubInTable.text = currentItem.position.toString()
            pointsClubInTable.text = currentItem.points.toString()
            matchesLose.text = currentItem.overall.lost.toString()
            matchesDraw.text = currentItem.overall.draw.toString()
            matchesWin.text = currentItem.overall.won.toString()
            matchesPlayed.text = currentItem.overall.games_played.toString()

            Picasso.get()
                .load(currentItem.team.logo)
                .into(logoClubForTable)

            if (currentTeamId == currentItem.team.team_id) {
                cellDetailsTable.setBackgroundResource(R.color.color_cell)
                setItemTextTypeface(this, Typeface.DEFAULT_BOLD)

            } else {
                setItemTextTypeface(this, Typeface.DEFAULT)
                cellDetailsTable.setBackgroundResource(R.color.white)
            }

            when (currentItem.result) {
                Status.CHAMPIONS_LEAGUE.value -> resultLabel.setBackgroundResource(R.color.purple_700)
                Status.EUROPA_LEAGUE.value -> resultLabel.setBackgroundResource(R.color.orange)
                Status.CONFERENCE_LEAGUE_QUALIFICATION.value -> resultLabel.setBackgroundResource(
                    android.R.color.holo_green_dark
                )
                Status.RELEGATION.value -> resultLabel.setBackgroundResource(R.color.red)
                else -> resultLabel.setBackgroundResource(R.color.white)
            }
        }
    }

    private fun setItemTextTypeface(binding: CellForDetailsTableBinding, typeface: Typeface) {
        binding.apply {
            pointsClubInTable.typeface = typeface
            positionClubInTable.typeface = typeface
            matchesPlayed.typeface = typeface
            matchesWin.typeface = typeface
            matchesDraw.typeface = typeface
            matchesLose.typeface = typeface
        }
    }

    override fun getItemCount() = tableList.size
}
