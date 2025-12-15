package com.notekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()



        splashScreen.setKeepOnScreenCondition {
            true
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

    }
}