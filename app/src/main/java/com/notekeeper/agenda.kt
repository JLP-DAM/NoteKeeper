package com.notekeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class agenda : AppCompatActivity() {

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agenda)

        val menuCardView = findViewById<CardView>(R.id.menu)
        val menuImageButton = findViewById<ImageButton>(R.id.menu_button)
        val closeMenuButton = findViewById<ImageButton>(R.id.menu_close_button)
        val createNoteImageButton = findViewById<ImageButton>(R.id.create_note)
        val binImageButton = findViewById<ImageButton>(R.id.bin)

        val configurationButton = findViewById<Button>(R.id.configuration)
        val shareButton = findViewById<Button>(R.id.share)
        val scheduleButton = findViewById<Button>(R.id.schedule)
        val profileButton = findViewById<Button>(R.id.profile)
        val homeButton = findViewById<Button>(R.id.btn_home)


        val calendarView = findViewById<CalendarView>(R.id.calendarView)

        menuImageButton.setOnClickListener {
            menuCardView.setLayoutParams(
                ViewGroup.LayoutParams(
                    dpToPx(300f, applicationContext),
                    applicationContext.resources.displayMetrics.heightPixels
                )
            )
        }

        closeMenuButton.setOnClickListener {
            menuCardView.setLayoutParams(
                ViewGroup.LayoutParams(
                    dpToPx(-300f, applicationContext),
                    applicationContext.resources.displayMetrics.heightPixels
                )
            )
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        configurationButton.setOnClickListener {
            val intent = Intent(this, Configuration::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener {
            val intent = Intent(this, compartir::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, perfil::class.java)
            startActivity(intent)
        }

        scheduleButton.setOnClickListener {
            menuCardView.setLayoutParams(
                ViewGroup.LayoutParams(
                    dpToPx(-300f, applicationContext),
                    applicationContext.resources.displayMetrics.heightPixels
                )
            )
        }

        createNoteImageButton.setOnClickListener {
            val intent = Intent(this, nota_agenda::class.java)
            startActivity(intent)
        }

        binImageButton.setOnClickListener {
            val intent = Intent(this, NoteBin::class.java)
            startActivity(intent)
        }

        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
        }
    }
}