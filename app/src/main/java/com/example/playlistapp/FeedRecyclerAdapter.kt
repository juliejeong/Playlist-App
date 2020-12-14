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
        var reqUser: TextView = itemView.findViewById(R.id.req_user)
        var reqBody: TextView = itemView.findViewById(R.id.req_body)
        var feedPosition: Int = 0

        /*
        init{
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply{
                    putExtra("position", feedPosition)
                    putExtra("postTitle", reqUser.text)
                    putExtra("postBody", posts[feedPosition].postBody)
                }
                context.startActivity(intent)
            }
        }

         */

        //responses button

        //request button

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feeditem_single, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reqUser.text = posts.get(position).reqUser
        holder.reqBody.text = posts.get(position).reqBody
        holder.feedPosition = position
    }

    override fun getItemCount() = posts.size
}