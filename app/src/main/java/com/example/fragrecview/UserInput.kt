package com.example.fragrecview

import app.User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import app.UserDao
import app.UserDatabase

class UserInput : Fragment() {
    private var database: UserDatabase? = null
    private var userDao: UserDao?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_input, container, false)
        val userID: EditText = view.findViewById(R.id.userId)
        val userName: EditText = view.findViewById(R.id.userName)
        val userPhone: EditText = view.findViewById(R.id.userPhone)
        val addUserBtn: Button = view.findViewById(R.id.addUser)
        database = UserDatabase.getDatabase((activity as MainActivity).applicationContext)
        userDao = database?.userDao()
        addUserBtn.setOnClickListener {
            val id = userID.text.toString()
            val name = userName.text.toString()
            val phone = userPhone.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty()) {
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

            loadFragment(UserDetails())
        }

        return view
    }
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
