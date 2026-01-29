package com.notekeeper

import android.content.Intent
import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.getValue
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val currentSession: Session by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { false }

        // Cargar layout
        setContentView(R.layout.activity_main)

        val homeFragment = Home()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, homeFragment)
            .commit()


        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        // Bottom Navigation
        bottomNav.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_home -> Home()
                R.id.nav_add -> Add()
                R.id.nav_settings -> Settings()
                R.id.nav_bin -> Bin()
                R.id.nav_profile -> LogOut()
                else -> null
            }

            // Si l'usuari esta logged in i han polsat el boto de profile el portarem a Profile
            // en comptes de LogOut
            if (item.itemId == R.id.nav_profile) {
                if (currentSession.isLoggedIn.value == true) {
                    selectedFragment = Profile()
                } else {
                    selectedFragment = LogOut()
                }
            }

            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit()
            }
            true
        }
    }

}

