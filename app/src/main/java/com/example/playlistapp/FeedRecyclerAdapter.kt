package com.example.playlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedRecyclerAdapter(private var posts: MutableList<FeedItemModel>, private val context: Context) : RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var postTitle: TextView = itemView.findViewById(R.id.postTitle)
        var postBody: TextView = itemView.findViewById(R.id.postBody)
        var feedPosition: Int = 0

        init{
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply{
                    putExtra("position", feedPosition)
                    putExtra("postTitle", postTitle.text)
                    putExtra("postBody", postBody.text)
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
        holder.postTitle.text = posts.get(position).postTitle
        holder.postBody.text = posts.get(position).postBody
        holder.feedPosition = position
    }

    override fun getItemCount() = posts.size
}