package com.notekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.activityViewModels
import kotlin.getValue

class Profile : Fragment() {

    private val currentSession: Session by activityViewModels()

    lateinit var userEmailTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        userEmailTextView = view.findViewById<TextView>(R.id.user_email)

        //Tenemos que indentificar el ImageButton por su ID
        val icChangePassword = view.findViewById<ImageButton>(R.id.icChangePassword)

        val icLogOut = view.findViewById<ImageButton>(R.id.icSalir)

        //Cunado lo des clic
        icLogOut.setOnClickListener {

            currentSession.setIsLoggedIn(false)
            currentSession.setEmail("")
            currentSession.setPassword("")

            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LogOut())
                .addToBackStack(null)
                .commit()
        }


        //Cunado lo des clic
        icChangePassword.setOnClickListener {

            //Permite passar de un fragment a otro
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ChangePassword())
                .addToBackStack(null)
                .commit()
        }

        currentSession.email.observe(viewLifecycleOwner) { newEmail ->
            userEmailTextView.text = newEmail
        }

        return view
    }
}