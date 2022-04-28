package com.example.citizen_care

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class LoginFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        var rootView: View = inflater.inflate(R.layout.fragment_login, container, false)

        rootView.findViewById<Button>(R.id.loginbtn).setOnClickListener{
            if (rootView.findViewById<EditText>(R.id.identification).getText().toString().equals("Blob3") &&
                rootView.findViewById<EditText>(R.id.password).getText().toString().equals("258kl")  ) {
                dismiss()
            }
        }

        return rootView
    }
}