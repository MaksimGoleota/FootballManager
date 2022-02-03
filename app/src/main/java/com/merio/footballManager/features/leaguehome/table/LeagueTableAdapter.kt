package com.merio.footballManager.features.leaguehome.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_for_table.view.*

class LeagueTableAdapter(
    private val itemClicks: (Int) -> Unit
) : RecyclerView.Adapter<LeagueTableAdapter.TableViewHolder>() {

    class TableViewHolder(View: View) : RecyclerView.ViewHolder(View) {

        val nameClubForTable: TextView = itemView.nameClubForTable
        val logoClubForTable: ImageView = itemView.logoClubForTable
        val positionClubInTable: TextView = itemView.positionClubInTable
        val pointsClubInTable: TextView = itemView.pointsClubInTable
        val resultLabel: View = itemView.resultLabel

    }

    private val tableList: MutableList<TableTeam> = mutableListOf()

    fun setData(table: List<TableTeam>) {
        tableList.clear()
        tableList.addAll(table)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.cell_for_table,
            parent, false
        )
        return TableViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {

        val currentItem = tableList[position]
        holder.positionClubInTable.text = currentItem.position.toString()
        holder.nameClubForTable.text = currentItem.team.name
        holder.pointsClubInTable.text = currentItem.points.toString()

        Picasso.get()
            .load(currentItem.team.logo)
            .into(holder.logoClubForTable)

        when (currentItem.result) {
            Status.CHAMPIONS_LEAGUE.value -> holder.resultLabel.setBackgroundResource(R.color.purple_700)
            Status.EUROPA_LEAGUE.value -> holder.resultLabel.setBackgroundResource(R.color.orange)
            Status.CONFERENCE_LEAGUE_QUALIFICATION.value -> holder.resultLabel.setBackgroundResource(
                android.R.color.holo_green_dark
            )
            Status.RELEGATION.value -> holder.resultLabel.setBackgroundResource(R.color.red)
            else -> holder.resultLabel.setBackgroundResource(R.color.white)
        }

        holder.itemView.setOnClickListener {
            itemClicks(currentItem.team.team_id)
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
