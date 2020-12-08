package com.example.playlistapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {
    private lateinit var feedView: RecyclerView
    private lateinit var feedAdapter: RecyclerView.Adapter<*>
    private lateinit var feedManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_feed, container, false)
        feedView = view.findViewById(R.id.feed_list)
        feedAdapter = FeedRecyclerAdapter(Repository.feedList, view.context)
        feedManager = LinearLayoutManager(context)

        feedView = feedView.apply{
            layoutManager = feedManager
            adapter = feedAdapter
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FeedFragment()
    }
}