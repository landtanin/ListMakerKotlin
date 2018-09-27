package com.raywenderlich.timefighter.listmaker_kotlin.listDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.raywenderlich.timefighter.listmaker_kotlin.MainActivity
import com.raywenderlich.timefighter.listmaker_kotlin.R
import com.raywenderlich.timefighter.listmaker_kotlin.TaskList

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)
        title = list.name
        listItemsRecyclerView = findViewById(R.id.list_detail_recyclerview)
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        listItemsRecyclerView.adapter = ListDetailRecyclerViewAdapter(list)

    }

}
