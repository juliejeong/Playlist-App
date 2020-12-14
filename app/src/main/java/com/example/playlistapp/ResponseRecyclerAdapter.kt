package com.example.playlistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResponseRecyclerAdapter(private var ress: MutableList<ResponseItemModel>, private val context: Context) : RecyclerView.Adapter<ResponseRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var resUser: TextView = itemView.findViewById(R.id.res_user)
        var resBody: TextView = itemView.findViewById(R.id.res_body)

        var responsePosition: Int = 0

        /*
        init{
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply{
                    putExtra("position", responsePosition)
                    putExtra("resUser", resUser.text)
                    putExtra("resBody", ress[responsePosition].resBody)
                }
                context.startActivity(intent)
            }
        }

         */

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.responseitem_single, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.resUser.text = ress.get(position).resUser
        holder.resBody.text = ress.get(position).resBody
    }

    override fun getItemCount() = ress.size
}