package com.raywenderlich.timefighter.listmaker_kotlin.listDetail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.raywenderlich.timefighter.listmaker_kotlin.R

class ListDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val taskTextView = itemView?.findViewById(R.id.textview_task) as TextView

//    class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        val listPosition: TextView = itemView.findViewById(R.id.itemNumber)
//        val listTitle: TextView = itemView.findViewById(R.id.itemString)
//
//    }

}