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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.adapter.UserAdapter
import com.example.fragrecview.data.local.userdata.User
import com.example.fragrecview.ui.MainActivity
import com.example.fragrecview.ui.userdetails.viewmodel.UserDetailsViewModel
import com.example.fragrecview.ui.userposts.UserPostsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var noUsersTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()

    private val rvAdapter: UserAdapter by lazy {
        UserAdapter { user ->
            userDetailsViewModel.deleteUser(user.userID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewUsers)
        noUsersTextView = view.findViewById(R.id.noUsers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = rvAdapter

        userDetailsViewModel.users.observe(viewLifecycleOwner) { users ->
            rvAdapter.updateData(users)
            updateUI(users)
        }

        userDetailsViewModel.fetchUsers()

        val addBtn: Button = view.findViewById(R.id.addMoreUsers)
        addBtn.setOnClickListener {
            val userInputFragment = UserInputFragment()
            activity?.let { mainActivity ->
                if (mainActivity is MainActivity) {
                    mainActivity.loadFragment(userInputFragment)
                }
            }
        }

        val showPostsButton: Button = view.findViewById(R.id.showPosts)
        showPostsButton.setOnClickListener {
            val userPostsFragment = UserPostsFragment()
            activity?.let { mainActivity ->
                if (mainActivity is MainActivity) {
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
