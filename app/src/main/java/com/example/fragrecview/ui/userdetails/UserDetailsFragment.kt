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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.MainActivity
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.adapter.UserAdapter
import com.example.fragrecview.data.local.User
import com.example.fragrecview.data.local.UserDatabase
import com.example.fragrecview.ui.userdetails.viewmodel.UserDetailsViewModel
import com.example.fragrecview.ui.userdetails.viewmodel.UserDetailsViewModelFactory
import com.example.fragrecview.ui.userposts.UserPostsFragment

class UserDetailsFragment : Fragment() {

    private lateinit var noUsersTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private val rvAdapter: UserAdapter by lazy {
        UserAdapter { user ->
            userDetailsViewModel.deleteUser(user.userID)
            val updatedUsers = userDetailsViewModel.getUsers()
            rvAdapter.updateData(updatedUsers)
            updateUI(updatedUsers)
        }
    }
    private lateinit var userDetailsViewModel: UserDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDetailsViewModel = ViewModelProvider(this, UserDetailsViewModelFactory(UserDatabase.getDatabase(requireContext()).userDao()))[UserDetailsViewModel::class.java]
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewUsers).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }

        noUsersTextView = view.findViewById(R.id.noUsers)

        val users = userDetailsViewModel.getUsers()
        rvAdapter.updateData(users)
        updateUI(users)

        view.findViewById<Button>(R.id.addMoreUsers).setOnClickListener {
            val userInputFragment = UserInputFragment()
            activity?.let { mainActivity ->
                if(mainActivity is MainActivity) {
                    mainActivity.loadFragment(userInputFragment)
                }
            }
        }

        view.findViewById<Button>(R.id.showPosts).setOnClickListener {
            val userPostsFragment = UserPostsFragment()
            activity?.let { mainActivity ->
                if(mainActivity is MainActivity) {
                    mainActivity.loadFragment(userPostsFragment)
                }
            }
        }
    }

    private fun updateUI(users: List<User>) {
        noUsersTextView.isVisible = users.isEmpty()
        recyclerView.isVisible = users.isNotEmpty()
    }
}
