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
 * Use the [ResponseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResponseFragment : Fragment() {
    private lateinit var responseView: RecyclerView
    private lateinit var responseAdapter: RecyclerView.Adapter<*>
    private lateinit var responseManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_response, container, false)
        responseView = view.findViewById(R.id.response_list)
        responseAdapter = ResponseRecyclerAdapter(Repository.responseList, view.context)
        responseManager = LinearLayoutManager(context)

        responseView = responseView.apply{
            layoutManager = responseManager
            adapter = responseAdapter
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResponseFragment()
    }
}