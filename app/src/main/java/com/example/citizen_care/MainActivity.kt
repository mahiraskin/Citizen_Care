package com.example.citizen_care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.citizen_care.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = LoginFragment()
        dialog.show(supportFragmentManager,"loginFragment")

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(ProfileFragment(), "Profile")
        //need to assign those to the values
        findViewById<ViewPager>(R.id.viewPager).adapter = adapter
        findViewById<TabLayout>(R.id.tabs).setupWithViewPager(findViewById<ViewPager>(R.id.viewPager))

        findViewById<TabLayout>(R.id.tabs).getTabAt(1)!!.setIcon(R.drawable.ic_baseline_album_24)
        findViewById<TabLayout>(R.id.tabs).getTabAt(0)!!.setIcon(R.drawable.ic_baseline_face_24)

    }
}