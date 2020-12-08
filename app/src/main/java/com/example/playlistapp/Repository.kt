package com.example.playlistapp

class Repository {
    companion object{
        var feedList = mutableListOf<FeedItemModel>()
        var notifList = mutableListOf<NotifItemModel>()
    }
}