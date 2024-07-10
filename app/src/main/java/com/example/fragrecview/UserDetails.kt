package com.example.fragrecview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.UserDao
import app.UserDatabase

class UserDetails : Fragment() {
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUsersTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_details, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewUsers)
        noUsersTextView = view.findViewById(R.id.noUsers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rvAdapter = UserAdapter()
        recyclerView.adapter = rvAdapter
        database = UserDatabase.getDatabase((activity as MainActivity).applicationContext)
        userDao = database.userDao()

        updateUI()

        val addBtn: Button = view.findViewById(R.id.addMoreUsers)
        addBtn.setOnClickListener {
            loadFragment(UserInput())
        }

        return view
    }

    private fun setData() {
        val users = userDao.getAll()
        rvAdapter.updateData(users)

    }

    override fun onResume() {
        super.onResume()
        setData()
        updateUI()
    }

    private fun updateUI() {
        val mainAct = activity as? MainActivity
        if (userDao.getAll().isEmpty()) {
            noUsersTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            noUsersTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
