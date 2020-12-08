package com.example.playlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationView

    // testing comment 4

    private var position = -1
    private var title = ""
    private var body = ""
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, ProfileFragment())
            .commit()

        bottomNavBar = findViewById(R.id.bottom_nav)
        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profile_item -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                }
                R.id.feed_item -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FeedFragment())
                        .commit()
                }
                R.id.notif_item -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, NotifFragment())
                        .commit()
                }
            }
            true
        }

//          TESTING THE FRAGMENTS AND STUFF
//        intent.extras?.let{
//            position = it.get("position") as Int
//            title = it.get("postTitle").toString()
//            body = it.get("postBody").toString()
//        }
//
//        button = findViewById(R.id.button)
//        button.setOnClickListener {
//            fragmentManager.beginTransaction().add(R.id.fragment_container, NotifFragment()).commit()
//        }
//
    }
}