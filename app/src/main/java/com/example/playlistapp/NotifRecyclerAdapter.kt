package com.example.playlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotifRecyclerAdapter(private var notifs: MutableList<NotifItemModel>, private val context: Context) : RecyclerView.Adapter<NotifRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var notifText: TextView = itemView.findViewById(R.id.notificationText)
        var notifPosition: Int = 0

        init{
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply{
                    putExtra("position", notifPosition)
                    putExtra("notifText", notifText.text)
                }
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notifitem_single, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notifText.text = notifs.get(position).notifText
        holder.notifPosition = position
    }

    override fun getItemCount() = notifs.size
}