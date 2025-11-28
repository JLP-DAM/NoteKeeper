package com.notekeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

fun dpToPx(dp: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val menuCardView = findViewById<CardView>(R.id.menu)
        val menuImageButton = findViewById<ImageButton>(R.id.menu_button)
        val closeMenuButton = findViewById<Button>(R.id.close_menu)
        val createNoteImageButton = findViewById<ImageButton>(R.id.create_note)

        closeMenuButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(-300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))
        }

        menuImageButton.setOnClickListener {
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))
        }

        createNoteImageButton.setOnClickListener {
            val intent = Intent(this, NoteEditor::class.java)
            startActivity(intent)
        }
    }
}