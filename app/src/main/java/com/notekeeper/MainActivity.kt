package com.notekeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

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
        setContentView(R.layout.main)

        BottomNavigationMenu().createMenu(findViewById(R.id.bottomNavigationView))
    }
}