package com.example.citizen_care

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        database = FirebaseDatabase.getInstance("https://citizen-care-default-rtdb.europe-west1.firebasedatabase.app").reference
        val moxieBar = rootView.findViewById<RatingBar>(R.id.moxieBar)
        val buyButton = rootView.findViewById<Button>(R.id.buyMoxie)
        val bodyCount = rootView.findViewById<TextView>(R.id.bodyCount)
        val profilePicture = rootView.findViewById<ImageView>(R.id.imageView)
        //update on change
        val xpAmount = rootView.findViewById<TextView>(R.id.xpAmount)

        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzbCx7cNE5XkCyj6BWGLCDDCinGZAdiLVk_eE1OkdaJfJJh8hkjVvvrGZSbGDlZ_dveho&usqp=CAU").into(
            profilePicture
        )

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.value
                xpAmount.text = post.toString()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        }
        database.child("Profile").child("Blob3").child("Xp").addValueEventListener(postListener)

        moxieBar.setIsIndicator(true)
        database.child("Profile").child("Blob3").child("Moxie").get().addOnSuccessListener {
            moxieBar.rating = it.value.toString().toFloat()
        }
        database.child("Profile").child("Blob3").child("Xp").get().addOnSuccessListener {
            xpAmount.text = it.value.toString()
        }
        database.child("Profile").child("Blob3").child("Clones").get().addOnSuccessListener {
            bodyCount.text = "Clones Left: " + it.value.toString()
        }

        buyButton.setOnClickListener {
            database.child("Profile").child("Blob3").child("Moxie").get().addOnSuccessListener {
                database.child("Profile").child("Blob3").child("Moxie").setValue(
                    ServerValue.increment(
                        1
                    )
                )
                moxieBar.rating = it.value.toString().toFloat()
            }
            database.child("Profile").child("Blob3").child("Xp").get().addOnSuccessListener {
                database.child("Profile").child("Blob3").child("Xp").setValue(
                    ServerValue.increment(
                        -50
                    )
                )
                xpAmount.text = it.value.toString()
            }
        }

        return rootView
    }
}