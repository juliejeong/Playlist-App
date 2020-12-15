package com.example.playlistapp

class Repository {
    companion object{
        var feedList = mutableListOf<FeedItemModel>()
        var responseList = mutableListOf<ResponseItemModel>()
        var friendsList = mutableListOf<FriendsItemModel>()
        lateinit var profileInfo: User
    }
}