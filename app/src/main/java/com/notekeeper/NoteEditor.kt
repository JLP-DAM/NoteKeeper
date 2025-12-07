package com.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class NoteEditor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.note_editor)

        val createNoteImageButton = findViewById<ImageButton>(R.id.create_note)
        val goBackImageButton = findViewById<ImageButton>(R.id.go_back)
        val configurationImageButton = findViewById<ImageButton>(R.id.configuration)
        val configurationCardView = findViewById<CardView>(R.id.configurationCardView)
        val deleteNoteButton = findViewById<ImageButton>(R.id.delete_note)

        createNoteImageButton.setOnClickListener {
            val intent = Intent(this, this::class.java)

            startActivity(intent)
        }

        goBackImageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        configurationImageButton.setOnClickListener {
            var layoutParams = ViewGroup.LayoutParams(configurationCardView.layoutParams.width, (configurationCardView.layoutParams.height * -1f).toInt())

            configurationCardView.layoutParams = layoutParams
        }

        deleteNoteButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}