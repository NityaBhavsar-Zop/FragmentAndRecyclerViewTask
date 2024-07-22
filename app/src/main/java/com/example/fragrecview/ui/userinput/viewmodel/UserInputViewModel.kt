package com.example.fragrecview.ui.userinput.viewmodel

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.fragrecview.R
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.data.local.userdata.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInputViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    fun getInput(view: View) {
        val userID: EditText = view.findViewById(R.id.userId)
        val userName: EditText = view.findViewById(R.id.userName)
        val userPhone: EditText = view.findViewById(R.id.userPhone)

        val id = userID.text.toString()
        val name = userName.text.toString()
        val phone = userPhone.text.toString()

        if (id.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
            val user = User(id, name, phone)
            userDao.insert(user)
            userID.text.clear()
            userName.text.clear()
            userPhone.text.clear()

            Toast.makeText(view.context, "User Added Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                view.context,
                "User was not added, enter all user details",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
