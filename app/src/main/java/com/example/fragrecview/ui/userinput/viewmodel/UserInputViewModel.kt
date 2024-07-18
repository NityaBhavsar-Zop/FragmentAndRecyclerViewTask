package com.example.fragrecview.ui.userinput.viewmodel

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.fragrecview.MainActivity
import com.example.fragrecview.R
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import com.example.fragrecview.data.local.userdata.UserDatabase

class UserInputViewModel : ViewModel() {
    private var database: UserDatabase? = null
    private var userDao: UserDao? = null

    fun getInput(context: Context) {
        database = UserDatabase.getDatabase(context.applicationContext)
        userDao = database?.userDao()

        val userID: EditText = (context as MainActivity).findViewById(R.id.userId)
        val userName: EditText = context.findViewById(R.id.userName)
        val userPhone: EditText = context.findViewById(R.id.userPhone)

        val id = userID.text.toString()
        val name = userName.text.toString()
        val phone = userPhone.text.toString()
        if (id.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
            val user = User(id, name, phone)
            userDao?.insert(user)
            userID.text.clear()
            userName.text.clear()
            userPhone.text.clear()

            val message = "User Added Successfully"
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val message = "User was not added, enter all user details"
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}
