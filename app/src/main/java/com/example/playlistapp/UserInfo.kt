package com.example.playlistapp

data class UserInfo(
    val id: String,
    val user: String,
    val username: String,
    val request: MutableList<SongRequest>?,
    val fav_songs: MutableList<Song>?,
    val friends: MutableList<User>?
)
