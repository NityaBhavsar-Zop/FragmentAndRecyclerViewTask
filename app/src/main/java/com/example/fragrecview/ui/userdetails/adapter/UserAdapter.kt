package com.example.fragrecview.ui.userdetails.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.data.local.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var users = listOf<User>()
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userIDTextView: TextView = itemView.findViewById(R.id.userIdTextView)
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val userPhoneTextView: TextView = itemView.findViewById(R.id.userPhoneTextView)

        fun setData(users: User) {
            with(users) {
                userIDTextView.text = userID
                userNameTextView.text = userName
                userPhoneTextView.text = userPhone
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_items, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.setData(user)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<User>) {
        users = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size
}
