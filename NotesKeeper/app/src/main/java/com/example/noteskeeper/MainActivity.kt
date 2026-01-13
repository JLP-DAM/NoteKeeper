package com.example.noteskeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash Screen
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { false }

        // Cargar layout
        setContentView(R.layout.activity_main)

        // Bottom Navigation
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_home -> Home()
                R.id.nav_profile -> Profile()
                R.id.nav_add -> Add()
                R.id.nav_settings -> Settings()
                R.id.bin -> Bin()
                else -> null
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
