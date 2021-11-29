package com.merio.footballManager.features.table

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merio.footballManager.R
import com.merio.footballManager.domain.data.network.models.TableTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.one_cell_for_table.view.*

class LeagueTableAdapter:
    RecyclerView.Adapter<LeagueTableAdapter.TableViewHolder>() {

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
            R.layout.one_cell_for_table,
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
            "Champions League" -> holder.resultLabel.setBackgroundResource(R.color.purple_700)
            "Europa League" -> holder.resultLabel.setBackgroundResource(R.color.orange)
            "Relegation" -> holder.resultLabel.setBackgroundResource(R.color.red)
        }

    }

    override fun getItemCount() = tableList.size
}
