package com.example.citizen_care

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.io.IOException
import java.io.InputStream

class Context() : DialogFragment() {
    companion object {

        fun newInstance(name: String): Context {
            val fragment = Context()

            val bundle = Bundle().apply {
                putString("Story", name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isCancelable = false
        val name = arguments?.getString("Story")

        val storyText : MutableList<String> = mutableListOf<String>()
        name?.lines()?.forEach {
            storyText.add(it)
        }

        var index = 0

        val rootView: View = inflater.inflate(R.layout.fragment_context, container, false)
        rootView.findViewById<TextView>(R.id.textView)?.text = "Press The Button"
        rootView.findViewById<Button>(R.id.button3).setOnClickListener{
            if(index == storyText.size) {
                dismiss()
            }
            rootView.findViewById<TextView>(R.id.textView)?.text = storyText[index].toString()
            if(index == storyText.size - 1) {
                it.findViewById<Button>(R.id.button3).text = "Finish"
            }
            index++
        }

        return rootView
    }
}