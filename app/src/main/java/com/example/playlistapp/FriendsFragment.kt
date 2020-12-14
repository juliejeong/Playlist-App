package com.example.playlistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendsFragment : Fragment() {
    private lateinit var friendsView: RecyclerView
    private lateinit var friendsAdapter: RecyclerView.Adapter<*>
    private lateinit var friendsManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_friends, container, false)
        friendsView = view.findViewById(R.id.searchFriends_list)
        friendsAdapter = FriendsRecyclerAdapter(Repository.friendsList, view.context)
        friendsManager = LinearLayoutManager(context)

        friendsView = friendsView.apply {
            layoutManager = friendsManager
            adapter = friendsAdapter
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FriendsFragment()
    }
}