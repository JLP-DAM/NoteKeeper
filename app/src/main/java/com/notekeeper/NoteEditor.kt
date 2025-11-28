package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class NoteEditor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.note_editor)

        val createNoteImageButton = findViewById<ImageButton>(R.id.go_back)

        createNoteImageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}