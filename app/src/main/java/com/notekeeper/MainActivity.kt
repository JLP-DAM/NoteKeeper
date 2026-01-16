package com.notekeeper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.PopupMenu
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var search: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        BottomNavigationMenu().createMenu(findViewById(R.id.bottomNavigationView))

        recyclerView = findViewById(R.id.notes)
        search = findViewById(R.id.search)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = TestNotes.items

        recyclerViewAdapter = RecyclerViewAdapter(
            items = items,
            onItemClick = { item ->
                Toast.makeText(
                    this,
                    "Has obert la nota: ${item.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        recyclerView.adapter = recyclerViewAdapter

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searched = s.toString().lowercase()

                applyFilter(null, searched)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        findViewById<ImageButton>(R.id.filter).setOnClickListener {
            showCategoryPopupMenu(findViewById(R.id.filter))
        }
    }

    private fun showCategoryPopupMenu(view: View) {
        val popup = PopupMenu(this, view)

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

    var searchedCategory = "All"
    var searchedName = ""
    private fun applyFilter(category: String?, nameFilter: String?) {
        if (category != null) {
            Toast.makeText(this, "Filtrat per: $category", Toast.LENGTH_SHORT).show()

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