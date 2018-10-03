package com.raywenderlich.timefighter.listmaker_kotlin.listDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.timefighter.listmaker_kotlin.MainActivity
import com.raywenderlich.timefighter.listmaker_kotlin.R
import com.raywenderlich.timefighter.listmaker_kotlin.TaskList

class ListDetailFragment : Fragment() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)
        view?.let {
            listItemsRecyclerView = it.findViewById(R.id.list_detail_recyclerview)
            listItemsRecyclerView.adapter = ListDetailRecyclerViewAdapter(list)
            listItemsRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

    fun addTask(item: String) {
        list.tasks.add(item)
        val listRecyclerAdapter = listItemsRecyclerView.adapter as ListDetailRecyclerViewAdapter
        listRecyclerAdapter.list = list
        listRecyclerAdapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ListDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(list: TaskList): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }
    }
}
