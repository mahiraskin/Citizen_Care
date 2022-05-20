package com.example.citizen_care

import android.os.Bundle
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
    ): View {
        isCancelable = false
        val rootView: View = inflater.inflate(R.layout.fragment_login, container, false)

        rootView.findViewById<Button>(R.id.loginbtn).setOnClickListener{
            if (rootView.findViewById<EditText>(R.id.identification).text.toString() == "Blob3" &&
                rootView.findViewById<EditText>(R.id.password).text.toString() == "258kl"
            ) {
                dismiss()
            }
        }

        return rootView
    }
}