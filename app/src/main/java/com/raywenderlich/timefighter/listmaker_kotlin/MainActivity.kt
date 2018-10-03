package com.raywenderlich.timefighter.listmaker_kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.FrameLayout
import com.raywenderlich.timefighter.listmaker_kotlin.listDetail.ListDetailActivity
import com.raywenderlich.timefighter.listmaker_kotlin.listDetail.ListDetailFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListSelectionFragment.OnFragmentInteractionListener {

    private var listselectionFragment: ListSelectionFragment = ListSelectionFragment()
    private var fragmentContainer: FrameLayout? = null
    private var largeScreen = false
    private var listFragment : ListDetailFragment? = null

    companion object {

        val INTENT_LIST_KEY = "list"
        val LIST_DETAIL_REQUEST_CODE = 123

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showCreateListDialog()
        }

        listselectionFragment = supportFragmentManager
                .findFragmentById(R.id.list_selection_fragment) as ListSelectionFragment
        fragmentContainer = findViewById(R.id.fragment_container)
        largeScreen = fragmentContainer != null

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        var builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialogInterface, i ->

            val list = TaskList(listTitleEditText.text.toString())
            listselectionFragment.addList(list)

            dialogInterface.dismiss()
            showListDetail(list)
        }

        builder.create().show()
    }

    private fun showListDetail(list: TaskList) {

        // if large screen is not exist, intent to the next screen
        if (!largeScreen) {
            val listDetailIntent = Intent(this, ListDetailActivity::class.java)
            listDetailIntent.putExtra(INTENT_LIST_KEY, list)
            startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)
        }
        // else, show the new screen on the large screen layout
        else {
            title = list.name
            listFragment = ListDetailFragment.newInstance(list)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, listFragment!!, getString(R.string.list_fragment_tag))
                    .addToBackStack(null)
                    .commit()

            // change the behavior of the fab button as we've moved to the detail screen
            fab.setOnClickListener {
                showCreateTaskDialog()
            }
        }


    }

    private fun showCreateTaskDialog() {

        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task, { dialogInterface, _ ->
                    val task = taskEditText.text.toString()
                    listFragment?.addTask(task)
                    dialogInterface.dismiss()
                })
                .create()
                .show()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        // manually save list on listFragment
        title = resources.getString(R.string.app_name)
        listFragment?.list?.let {
            listselectionFragment?.listDataManager?.saveList(it)
        }

        // delete listDetailFragment from the fragment stack
        if (listFragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .remove(listFragment!!)
                    .commit()
            listFragment = null
        }

        // reassign the fab once we come back to the main screen
        fab.setOnClickListener {
            showCreateListDialog()
        }

    }

    override fun onListItemClicked(list: TaskList) {
        showListDetail(list)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LIST_DETAIL_REQUEST_CODE) {
            data?.let {

                listselectionFragment.saveList(data.getParcelableExtra(INTENT_LIST_KEY))

            }
        }
    }

}
