package com.example.playlistapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

private val client = OkHttpClient()

private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
private val userInfoJsonAdapter : JsonAdapter<UserInfo> = moshi.adapter(UserInfo:: class.java)
private val reqJsonAdapter : JsonAdapter<Requests> = moshi.adapter(Requests:: class.java)
private val recJsonAdapter : JsonAdapter<Recommendations> = moshi.adapter(Recommendations:: class.java)

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavBar: BottomNavigationView
    private var title = ""
    private var body = ""
    private var myId = 1
    lateinit var myUser : User
    private var myUserInfo: UserInfo = UserInfo("0", "NAME", "@username", null, null, null)
    private var myReq = mutableListOf<SongRequest>()
    private var myRec = mutableListOf<Recommendation>()

    private var feedList = mutableListOf<FeedItemModel>()
    private var responseList = mutableListOf<ResponseItemModel>()
    private var friendsList = mutableListOf<FriendsItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedMockData()
        friendsMockData()
        responsesMockData()

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

        //retrieve user info
        get_user_by_id(myId)

        //retreive requests from a user
        get_requests_by_user(myId)

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

    //get_user_by_id(user_id)	(GET method)
    //given a user’s id, returns a serialized user, with the user’s id #, name, username, serialized requests, and serialized favorite songs
    private fun get_user_by_id(user_id: Int){
        val requestUserInfo = Request.Builder()
                .url("https://cadenzaapp.herokuapp.com/api/users/{${user_id.toString()}}/").build()

        client.newCall(requestUserInfo).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) throw IOException("Unexpected code $it")

                    val received: UserInfo? = userInfoJsonAdapter.fromJson(response.body!!.string())
                    if (received != null) {
                        myUserInfo = received

                        Log.d("gotcha_user", received.toString())
                    }
                }
            }
        })

        //retreive UserInfo
        if (myUserInfo.friends != null) {
            for (friend in myUserInfo.friends!!) {
                friendsList.add(FriendsItemModel(friend.user, friend.username))
            }
            Repository.friendsList = friendsList
        }

        //fill out user info for profile
        Repository.profileInfo = User(myUserInfo.id, myUserInfo.user, myUserInfo.username)
    }

    //get_requests_by_user(user_id) 	(GET method)
    //given a user’s id, returns a user’s requests
    private fun get_requests_by_user (user_id: Int){
        val requestReqs = Request.Builder()
                .url("https://cadenzaapp.herokuapp.com/api/users/{${user_id.toString()}}/requests/").build()

        client.newCall(requestReqs).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use{
                    if(!it.isSuccessful) throw IOException("Unexpected code $it")

                    //response.body?.string()?.let { it1 -> Log.d("julie", it1) }

                    val received: Requests? = reqJsonAdapter.fromJson(response.body!!.string())
                    if (received != null) {
                        myReq = received.requests
                        Log.d("gotcha_req", received.toString())
                    }
                }
            }
        })

        //add requests to the feedlist
        for (req in myReq){
            feedList.add(FeedItemModel(req.id.toString(), req.message))
        }
       // Repository.feedList = feedList
    }

    //get_recommendation_by_id(rec_id) 	(GET method)
    //returns a recommendation, given the recommendation id #
    private fun get_recommendation_by_id (rec_id: Int){
        val requestRecs = Request.Builder()
                .url("https://cadenzaapp.herokuapp.com/api/recommendations/{${rec_id.toString()}}/").build()

        client.newCall(requestRecs).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use{
                    if(!it.isSuccessful) throw IOException("Unexpected code $it")

                    val received: Recommendations? = recJsonAdapter.fromJson(response.body!!.string())
                    if (received != null) {
                        myRec = received.recommendations
                        Log.d("gotcha_rec", received.toString())
                    }
                }
            }
        })

        //add responses to the responselist
        for (rec in myRec){
            responseList.add(ResponseItemModel(rec.id.toString(), rec.message))
        }

        //Repository.responseList = responseList
    }

    private fun recommendSong(title: String, body: String) {
        bottomNavBar.selectedItemId = R.id.feed_item
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecommendFragment.newInstance(title, body)).commit()
    }

    private fun feedMockData() {
        var mockData = mutableListOf<FeedItemModel>()
        mockData.add(FeedItemModel("Finals coming up...", "Please recommend me songs for studying"))
        mockData.add(FeedItemModel("Hellloooo :)", "I want to listen to some upbeat songs. recs?"))
        mockData.add(FeedItemModel("sad songs", "i'm not feeling well ;-;"))
        for (i in 3..6) {
            mockData.add(FeedItemModel("PostTitle " + i, "Example Body Text...."))
        }
        Repository.feedList = mockData
    }

    private fun friendsMockData() {
        var mockData = mutableListOf<FriendsItemModel>()
        mockData.add(FriendsItemModel("Arianna C.", "arianna_curillo" ))
        mockData.add(FriendsItemModel("Chloe Chu", "chloechu" ))
        mockData.add(FriendsItemModel("Connie", "connie_000" ))
        mockData.add(FriendsItemModel("Michelle Gao", "michelle___gao" ))
        mockData.add(FriendsItemModel("Julie J.", "julie.jeong" ))

        for (i in 6..9) {
            mockData.add(FriendsItemModel("Friend Name " + i, "f_username_" + i))
        }
        Repository.friendsList = mockData
    }

    private fun responsesMockData() {
        var mockData = mutableListOf<ResponseItemModel>()
        mockData.add(ResponseItemModel("Here is my rec!", "I hope you like it"))
        mockData.add(ResponseItemModel("BEST SONG", "you will love it <3"))
        mockData.add(ResponseItemModel("song rec", "lmk what you think"))

        for (i in 4..7) {
            mockData.add(ResponseItemModel("Song recommendation " + i, "message " + i))
        }
        Repository.responseList = mockData
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
