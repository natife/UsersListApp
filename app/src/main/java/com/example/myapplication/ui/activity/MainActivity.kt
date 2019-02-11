package com.example.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView


import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.example.myapplication.model.Group
import com.example.myapplication.ui.adapters.GroupsRecyclerAdapter



class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModel()

    private var groupsRecyclerAdapter: GroupsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myapplication.R.layout.activity_main)
        subscribeToModel()
        viewModel.loadGroupsList()
    }

    private fun subscribeToModel(){
        progressbar.visibility = View.VISIBLE
        viewModel.groupsList.observe(this, Observer {
                groups -> setAdapter(groups)
        })
    }

    private fun setAdapter(groups: List<Group>){
        progressbar.visibility = View.GONE
        rvGroups.layoutManager = LinearLayoutManager(this)
        groupsRecyclerAdapter = GroupsRecyclerAdapter(ArrayList(groups), this)
        rvGroups.adapter = groupsRecyclerAdapter


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.query_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                groupsRecyclerAdapter?.search(query)
                return false
            }

        })
        searchView.setOnQueryTextFocusChangeListener { _ , hasFocus ->
            if (hasFocus) {

            } else {
                groupsRecyclerAdapter?.search("")
            }
        }


        return true
    }
}
