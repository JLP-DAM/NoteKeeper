package com.notekeeper

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


fun dpToPx(dp: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.getResources().getDisplayMetrics()
    ).toInt()
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val menuCardView = findViewById<CardView>(R.id.menu)
        val menuImageButton = findViewById<ImageView>(R.id.menu_button)

        menuImageButton.setOnClickListener {
            val displayMetrics = DisplayMetrics()

            Log.d("OKAY", "THIS WORKS");
            Log.d("SIZE", "" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, resources.displayMetrics).toInt())
            menuCardView.setLayoutParams(ViewGroup.LayoutParams(dpToPx(300f, applicationContext), applicationContext.resources.displayMetrics.heightPixels))

        }
    }
}