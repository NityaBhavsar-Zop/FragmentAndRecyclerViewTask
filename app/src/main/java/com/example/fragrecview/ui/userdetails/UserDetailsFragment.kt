package com.example.fragrecview.ui.userdetails

import com.example.fragrecview.ui.userinput.UserInputFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.adapter.UserAdapter
import com.example.fragrecview.data.local.User
import com.example.fragrecview.ui.userdetails.viewmodel.UserDetailsViewModel
import com.example.fragrecview.ui.userposts.UserPostsFragment

class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUsersTextView: TextView
    private lateinit var recyclerView: RecyclerView

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

        userDetailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)

        userDetailsViewModel.getUsers(requireContext()).observe(viewLifecycleOwner, Observer { users:List<User> ->
            rvAdapter.updateData(users)
            updateUI(users)
        })

        val addBtn: Button = view.findViewById(R.id.addMoreUsers)
        addBtn.setOnClickListener {
            loadFragment(UserInputFragment())
        }

        val showPostsButton: Button = view.findViewById(R.id.showPosts)
        showPostsButton.setOnClickListener {
            loadFragment(UserPostsFragment())
        }

        return view
    }

    private fun updateUI(users: List<User>) {
        noUsersTextView.isVisible = users.isEmpty()
        recyclerView.isVisible = users.isNotEmpty()
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}
