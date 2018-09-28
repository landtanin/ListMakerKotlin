package com.raywenderlich.timefighter.listmaker_kotlin.listDetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raywenderlich.timefighter.listmaker_kotlin.R
import com.raywenderlich.timefighter.listmaker_kotlin.TaskList

class ListDetailRecyclerViewAdapter(var list: TaskList): RecyclerView.Adapter<ListDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.task_view_holder, parent, false)
        return ListDetailViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: ListDetailViewHolder, position: Int) {
        if (holder != null) {
            holder.taskTextView?.text = list.tasks[position]
        }
    }

}