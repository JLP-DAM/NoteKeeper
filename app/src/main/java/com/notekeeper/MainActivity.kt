package com.notekeeper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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

                if (searched.isEmpty()) {return;}

                for (note in recyclerView.children) {
                    val name = note.findViewById<TextView>(R.id.tvTitle).text.toString().lowercase()

                    Log.d("SEARCHED", searched + ", " + name + ", " + name.substring(0, (searched.length - 1).coerceAtLeast(1)))



                    if (searched == name.substring(0, (searched.length).coerceIn(1, name.length))) {
                        note.visibility = View.VISIBLE;
                    } else {
                        note.visibility = View.GONE;
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}