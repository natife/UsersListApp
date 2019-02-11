package com.example.myapplication.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.contact_item.view.*

class UsersRecyclerViewAdapter (private val items: List<User>, private val context: Context) : RecyclerView.Adapter<UsersRecyclerViewAdapter.UserRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserRecyclerViewHolder(
        LayoutInflater.from(context).inflate(
        R.layout.contact_item, parent, false))

    override fun onBindViewHolder(holder: UserRecyclerViewHolder, position: Int) = holder.bind(items[position], context)

    override fun getItemCount() = items.size


    class UserRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, context: Context)  {
            itemView.tvUsername.text = "${user.firstName}, ${user.lastName}"
            itemView.tvStatus.text = user.statusMessage
            when (user.statusIcon){
                User.Status.OFFLINE.status -> itemView.ivStatus.setImageDrawable(context.getDrawable(R.drawable.contacts_list_status_offline2))
                User.Status.ONLINE.status -> itemView.ivStatus.setImageDrawable(context.getDrawable(R.drawable.contacts_list_status_online2))
                User.Status.BUSY.status -> itemView.ivStatus.setImageDrawable(context.getDrawable(R.drawable.contacts_list_status_busy2))
                User.Status.AWAY.status -> itemView.ivStatus.setImageDrawable(context.getDrawable(R.drawable.contacts_list_status_away2))
                User.Status.CALLFORWARDING.status -> itemView.ivStatus.setImageDrawable(context.getDrawable(R.drawable.contacts_list_call_forward2))
            }
        }
    }
}