package com.notekeeper

import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Context
import android.content.Intent

class BottomNavigationMenu {
    public fun createMenu(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.context

        bottomNavigationView.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.home -> bottomNavigationView.context.startActivity(Intent(bottomNavigationView.context, MainActivity::class.java))
                R.id.bin -> bottomNavigationView.context.startActivity(Intent(bottomNavigationView.context,  Bin::class.java))
                R.id.create_note -> bottomNavigationView.context.startActivity(Intent(bottomNavigationView.context, NoteEditor::class.java))
                R.id.profile -> bottomNavigationView.context.startActivity(Intent(bottomNavigationView.context, Profile::class.java))
                R.id.settings -> bottomNavigationView.context.startActivity(Intent(bottomNavigationView.context, Configuration::class.java))
            }

            true
        }
    }
}