package com.example.fragrecview.ui.userdetails.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.data.local.userdata.User

class UserAdapter(private val onDeleteClickListener: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var users = listOf<User>()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userIDTextView: TextView = itemView.findViewById(R.id.userIdTextView)
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val userPhoneTextView: TextView = itemView.findViewById(R.id.userPhoneTextView)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteUser)

        fun bind(user: User, onDeleteClickListener: (User) -> Unit) {
            with(user) {
                userIDTextView.text = userID
                userNameTextView.text = userName
                userPhoneTextView.text = userPhone
                deleteButton.setOnClickListener {
                    onDeleteClickListener.invoke(user)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_items, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user, onDeleteClickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<User>) {
        users = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size
}
