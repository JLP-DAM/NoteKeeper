package com.notekeeper

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(
    itemView: View,
    private val onItemClick: (NoteListItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val tvSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)

    fun bind(item: NoteListItem) {
        Log.d(item.name, item.text)
        tvTitle.text = item.name
        tvSubtitle.text = item.text

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}