package com.notekeeper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var search: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)

        val currentView = homeFragmentView

        if (currentView != null) {
            recyclerView = currentView.findViewById<RecyclerView>(R.id.notes)
            search = currentView.findViewById(R.id.search)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)

        val items = TestNotes.items

        // Creem l'adapter i preparem el onItemClick que en aquest cas obriria la nota
        // (de moment sol obre el fragment de "Add" per crear una nota, ja que de moment no hi ha
        // el "NoteEditor" del prototip)
        recyclerViewAdapter = RecyclerViewAdapter(
            items = items,
            onItemClick = { item ->
                Toast.makeText(
                    context,
                    "Has obert la nota: ${item.name} (Editor de notes en progrés...)",
                    Toast.LENGTH_SHORT
                ).show()

                val addFragment = Add()

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, addFragment)
                    ?.commit()
            }
        )

        recyclerView.adapter = recyclerViewAdapter

        // Escoltem canvis a la cerca per aplicar el filtre
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searched = s.toString().lowercase()

                applyFilter(null, searched)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Escoltem que el botó de filtre es cliqui per mostrar el popup menu de cerca
        if (currentView != null) {
            currentView.findViewById<ImageButton>(R.id.filter).setOnClickListener {
                showCategoryPopupMenu(currentView.findViewById(R.id.filter))
            }
        }

        return homeFragmentView
    }

    // Mostrem el menu de popup per filtrar per categories
    private fun showCategoryPopupMenu(view: View) {
        val popup = PopupMenu(context, view)

        popup.menuInflater.inflate(R.menu.note_filter, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.category_all -> {
                    applyFilter("All", null)
                    true
                }

                R.id.category_normal -> {
                    applyFilter("Normal", null)
                    true
                }

                R.id.category_agenda -> {
                    applyFilter("Agenda", null)
                    true
                }

                R.id.category_shared -> {
                    applyFilter("Shared", null)
                    true
                }

                else -> false
            }
        }

        popup.show()
    }

    /*
        Aqui tenim el filtre que segons la categoria buscada i el text que s'està escrivint ara
        mateix actualitza la llista base per mostrar les notes (items) que pertanyen a la llista
    */
    var searchedCategory = "All"
    var searchedName = ""
    private fun applyFilter(category: String?, nameFilter: String?) {
        if (category != null) {
            Toast.makeText(context, "Filtrat per: $category", Toast.LENGTH_SHORT).show()

            searchedCategory = category
        }

        if (nameFilter != null) {
            searchedName = nameFilter
        }

        var notes: ArrayList<NoteListItem> = ArrayList()

        for (note in TestNotes.items) {
            if (note.type != searchedCategory && searchedCategory != "All") {continue}

            val name = note.name.lowercase()

            if ((!searchedName.isEmpty()) && (searchedName != name.substring(0, (searchedName.length).coerceIn(1, name.length)))) {continue}

            notes.add(note)
        }

        recyclerViewAdapter.updateList(notes)
    }
}