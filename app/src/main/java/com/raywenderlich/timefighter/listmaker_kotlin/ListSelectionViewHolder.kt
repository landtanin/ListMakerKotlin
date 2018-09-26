package com.raywenderlich.timefighter.listmaker_kotlin

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val listPosition: TextView = itemView.findViewById(R.id.itemNumber)
    val listTitle: TextView = itemView.findViewById(R.id.itemString)

}