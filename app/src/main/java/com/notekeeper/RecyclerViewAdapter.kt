package com.notekeeper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
   Aquest es l'adapter, es l'abstracció que ens permet interactuar entre els fitxers de Kotlin
   i el holder
 */
class  RecyclerViewAdapter(
    private var items: List<NoteListItem>,
    private val onItemClick: (NoteListItem) -> Unit
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    // Crea el ViewHolder per cada nota
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.note, parent, false)
        return RecyclerViewHolder(view, onItemClick)
    }

    // Retorna la quantitat d'items que hi ha
    override fun getItemCount(): Int = items.size

    // Crida al Holder per "crear" la nota (Item) en forma de View (CardView)
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    // Per ensenyar la llista modificada enviem una nova llista i cridem notifyDataSetChanged()
    // perque el RecyclerView s'actualitzi amb els elements indicats a la llista
    // (com, per exemple una llista que sol ensenya notes de tipus "Compartides")
    fun updateList(newList: List<NoteListItem>) {
        items = newList
        notifyDataSetChanged()
    }
}