package com.example.myapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Group

import kotlinx.android.synthetic.main.group_item.view.*

class GroupsRecyclerAdapter (private val groups: ArrayList<Group>, private val context: Context) : RecyclerView.Adapter<GroupsRecyclerAdapter.GroupRecyclerViewHolder>() {

    private val filteredGroups: ArrayList<Group> = ArrayList(groups)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GroupRecyclerViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.group_item, parent, false))

    override fun onBindViewHolder(holder: GroupRecyclerViewHolder, position: Int) = holder.bind(filteredGroups.get(position), context)

    override fun getItemCount() = filteredGroups.size


    fun search(query: String){
        filteredGroups.clear()
        if (query.isNullOrEmpty()){
            filteredGroups.addAll(groups)
        }
        else{
            filteredGroups.clear()
            val iterator = groups.iterator()
            for (group in iterator){
                val userIterator = group.peoplesList.iterator()
                for (user in userIterator){
                    if (user.firstName.toLowerCase() == query.toLowerCase() || user.lastName.toLowerCase() == query.toLowerCase()){
                        filteredGroups.add(group)
                        break
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    class GroupRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(group: Group, context: Context)  {
            itemView.tvGroupTitle.text = group.groupName
            itemView.rvContacts.layoutManager = LinearLayoutManager(context)
            val usersRecyclerViewAdapter = UsersRecyclerViewAdapter(group.peoplesList, context)
            itemView.rvContacts.adapter = usersRecyclerViewAdapter

            itemView.llHeader.setOnClickListener({ v -> onHeaderClick(context) })
        }

        fun onHeaderClick(context: Context){
            if (itemView.rvContacts.visibility == View.VISIBLE){
                itemView.rvContacts.visibility = View.GONE
                itemView.ivExpandStstus.setImageDrawable(context.getDrawable(R.drawable.ic_keyboard_arrow_down))
            }
            else{
                itemView.rvContacts.visibility = View.VISIBLE
                itemView.ivExpandStstus.setImageDrawable(context.getDrawable(R.drawable.ic_keyboard_arrow_up))
            }
        }
    }



}