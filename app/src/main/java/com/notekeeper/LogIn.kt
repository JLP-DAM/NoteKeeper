package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class LogIn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_log_in, container, false)

        val btnLogIn = view.findViewById<Button>(R.id.btnLogIn)

        btnLogIn.setOnClickListener {
            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Profile())
                .addToBackStack(null)
                .commit()
        }

        val btnInciarSession = view.findViewById<Button>(R.id.btnInciarSession)

        btnInciarSession.setOnClickListener {
            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, SignIn())
                .addToBackStack(null)
                .commit()
        }



        return view
    }
}