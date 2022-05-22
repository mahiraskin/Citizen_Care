package com.example.citizen_care

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginFragment : DialogFragment() {
    private lateinit var database: DatabaseReference

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        isCancelable = false
        database = FirebaseDatabase.getInstance("https://citizen-care-default-rtdb.europe-west1.firebasedatabase.app").reference
        val rootView: View = inflater.inflate(R.layout.fragment_login, container, false)
        lateinit var name: String
        lateinit var password: String

        rootView.findViewById<Button>(R.id.loginbtn).setOnClickListener{
            name = rootView.findViewById<EditText>(R.id.identification).text.toString()
            password = rootView.findViewById<EditText>(R.id.password).text.toString()
            database.child("Citizen").child(name).get().addOnSuccessListener {
                if(it.value.toString() == password)
                    dismiss()
                else {
                    rootView.findViewById<Button>(R.id.loginbtn).text = "wrong username or password"
                }
            }.addOnFailureListener{
                rootView.findViewById<Button>(R.id.loginbtn).text = "Database down"
            }
        }

        return rootView
    }
}