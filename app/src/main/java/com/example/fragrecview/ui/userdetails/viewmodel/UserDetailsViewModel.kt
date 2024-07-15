package com.example.fragrecview.ui.userdetails.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragrecview.data.local.User
import com.example.fragrecview.data.local.UserDatabase

class UserDetailsViewModel : ViewModel() {

    fun getUsers(context: Context): LiveData<List<User>> {
        val database = UserDatabase.getDatabase(context)
        val userDao = database.userDao()
        val users = userDao.getAll()
        val liveData = MutableLiveData<List<User>>()
        liveData.value = users
        return liveData
    }
}
