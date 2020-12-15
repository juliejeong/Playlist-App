package com.example.playlistapp

data class SongRequest(
    val id: Int,
    val genre: String,
    val message: String,
    val recommendations: MutableList<Recommendation>
)
