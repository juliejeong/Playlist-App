package com.example.playlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedRecyclerAdapter(private var posts: MutableList<FeedItemModel>, private val context: Context) : RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var feedUser: TextView = itemView.findViewById(R.id.feed_user)
        var postBody: TextView = itemView.findViewById(R.id.post_body)
        var recommendButton: Button = itemView.findViewById(R.id.button_rec)
        var responseButton: Button = itemView.findViewById(R.id.button_res)

        var feedPosition: Int = 0

        init {
            recommendButton.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("position", feedPosition)
                    putExtra("postTitle", feedUser.text)
                    putExtra("postBody", postBody.text)
                    putExtra("recommend?", true)
                }
                context.startActivity(intent)
            }

            responseButton.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("responses?", true)
                }
                context.startActivity(intent)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feeditem_single, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.feedUser.text = posts.get(position).feedUser
        holder.postBody.text = posts.get(position).postBody
        holder.feedPosition = position
    }

    override fun getItemCount() = posts.size
}