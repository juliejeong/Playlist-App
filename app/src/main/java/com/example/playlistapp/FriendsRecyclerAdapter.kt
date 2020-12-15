package com.example.playlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendsRecyclerAdapter(
    private var friends: MutableList<FriendsItemModel>,
    private val context: Context
) : RecyclerView.Adapter<FriendsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var user: TextView = itemView.findViewById(R.id.user)
        var username: TextView = itemView.findViewById(R.id.username)
        var followButton: Button = itemView.findViewById(R.id.button_follow)
        var friendsPosition: Int = 0

        init {
            followButton.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("following?", true)
                }
                context.startActivity(intent)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friendsitem_single, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.user.text = friends.get(position).user
        holder.username.text = friends.get(position).username
        holder.friendsPosition = position
    }

    override fun getItemCount() = friends.size
}