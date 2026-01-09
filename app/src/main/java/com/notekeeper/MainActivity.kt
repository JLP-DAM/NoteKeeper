package com.notekeeper

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        BottomNavigationMenu().createMenu(findViewById(R.id.bottomNavigationView))

        recyclerView = findViewById(R.id.notes)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = TestNotes.items

        recyclerViewAdapter = RecyclerViewAdapter(
            items = items,
            onItemClick = { item ->
                Toast.makeText(
                    this,
                    "Has obert: ${item.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        recyclerView.adapter = recyclerViewAdapter
    }
}