package com.example.playlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationView
    private var title = ""
    private var body = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mockDataAdd()

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

        intent.extras?.let {
            title = it.get("postTitle").toString()
            body = it.get("postBody").toString()
            recommendSong(title, body)
        }


    }

    private fun recommendSong(title: String, body: String) {
        bottomNavBar.selectedItemId = R.id.feed_item
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RecommendFragment.newInstance(title, body)).commit()
    }

    private fun mockDataAdd() {
        val numPosts: List<Int> = listOf(1, 2, 3)
        var mockData = mutableListOf<FeedItemModel>()
        for (i in numPosts) {
            mockData.add(FeedItemModel("PostTitle" + i, "Example Body Text"))
        }
        Repository.feedList = mockData
    }
}