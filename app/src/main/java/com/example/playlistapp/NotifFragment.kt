package com.example.playlistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotifFragment : Fragment() {
    private lateinit var notifView: RecyclerView
    private lateinit var notifAdapter: RecyclerView.Adapter<*>
    private lateinit var notifManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_notif, container, false)
        notifView = view.findViewById(R.id.notif_list)
        notifAdapter = NotifRecyclerAdapter(Repository.notifList, view.context)
        notifManager = LinearLayoutManager(context)

        notifView = notifView.apply{
            layoutManager = notifManager
            adapter = notifAdapter
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotifFragment()
    }
}