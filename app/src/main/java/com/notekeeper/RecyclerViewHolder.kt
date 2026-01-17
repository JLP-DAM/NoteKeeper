package com.notekeeper

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Aquesta classe s'encarrega de "traduir" les notes (items) a el texte dins d'una View
// (O altres coses)
class RecyclerViewHolder(
    itemView: View,
    private val onItemClick: (NoteListItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.name)
    private val testTextView: TextView = itemView.findViewById(R.id.text)

    // Connecta el "Item" a la CardView i "connecta" el listener per activar la funció onItemClick
    // al clicar-ho que s'ha enviat previament
    fun bind(item: NoteListItem) {
        nameTextView.text = item.name
        testTextView.text = item.text

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }
}