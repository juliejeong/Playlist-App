package com.example.playlistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

// feed : buttons, routes to responses & request fragments - done
// naviagation: change notif --> request - done
// if enough time, find friends
// network !!!!

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationView
    private var title = ""
    private var body = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feedMockData()

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
                R.id.req_item -> {
                    fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RequestFragment())
                        .commit()
                }
            }
            true
        }

        intent.extras?.let {
            if (it.get("recommend?") != null) {
                val displayRecommendFragment: Boolean = it.get("recommend?") as Boolean
                if (displayRecommendFragment) {
                    title = it.get("postTitle").toString()
                    body = it.get("postBody").toString()
                    recommendSong(title, body)
                }
            }
        }

        intent.extras?.let {
            if (it.get("responses?") != null) {
                val displayResponses: Boolean = it.get("responses?") as Boolean
                if (displayResponses) {
                    showResponses()
                }
            }
        }

        intent.extras?.let {
            if (it.get("find friends?") != null) {
                val displayFindFriends: Boolean = it.get("find friends?") as Boolean
                if (displayFindFriends) {
                    searchForFriends()
                }
            }
        }


    }

    private fun recommendSong(title: String, body: String) {
        bottomNavBar.selectedItemId = R.id.feed_item
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecommendFragment.newInstance(title, body)).commit()
    }

    private fun feedMockData() {
        var mockData = mutableListOf<FeedItemModel>()
        for (i in 1..10) {
            mockData.add(FeedItemModel("PostTitle" + i, "Example Body Text"))
        }
        Repository.feedList = mockData
    }

    private fun showResponses() {
        bottomNavBar.selectedItemId = R.id.feed_item
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ResponseFragment()).commit()
    }

    private fun searchForFriends() {
        bottomNavBar.selectedItemId = R.id.profile_item
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, FriendsFragment())
            .commit()
    }

}
