package com.notekeeper

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(
    itemView: View,
    private val onItemClick: (NoteListItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val textViewTitle: TextView = itemView.findViewById(R.id.tvTitle)
    private val textViewSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)

    fun bind(item: NoteListItem) {
        textViewTitle.text = item.name
        textViewSubtitle.text = item.text

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}