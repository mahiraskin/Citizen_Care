package com.example.citizen_care

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val ratingBar = rootView.findViewById<RatingBar>(R.id.moxieBar)
        val buyButton = rootView.findViewById<Button>(R.id.buyMoxie)
        val bodyCount = rootView.findViewById<TextView>(R.id.bodyCount)

        ratingBar.setIsIndicator(true)

        return rootView
    }
}