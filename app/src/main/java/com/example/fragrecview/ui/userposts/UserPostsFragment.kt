package com.example.fragrecview.ui.userposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.MainActivity
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userposts.adapter.PostsAdapter
import com.example.fragrecview.ui.userposts.viewmodel.UserPostsViewModel

class UserPostsFragment : Fragment() {

    private val rvAdapter: PostsAdapter by lazy {
        PostsAdapter()
    }
    private lateinit var userPostsViewModel: UserPostsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPostsViewModel = ViewModelProvider(this)[UserPostsViewModel::class.java]

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPosts).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }

        userPostsViewModel.getPosts { posts ->
            rvAdapter.updateData(posts)
        }

        view.findViewById<Button>(R.id.goBackButton).setOnClickListener {
            val userDetailsFragment = UserDetailsFragment()
            activity?.let { mainActivity ->
                if (mainActivity is MainActivity) {
                    mainActivity.loadFragment(userDetailsFragment)
                }
            }
        }
    }
}
