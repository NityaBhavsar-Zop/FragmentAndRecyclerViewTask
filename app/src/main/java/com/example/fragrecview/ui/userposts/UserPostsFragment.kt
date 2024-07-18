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
import com.example.fragrecview.data.local.userdata.UserDatabase
import com.example.fragrecview.data.remote.GetRetrofitInstance
import com.example.fragrecview.data.repository.PostsRepository
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userposts.adapter.PostsAdapter
import com.example.fragrecview.ui.userposts.viewmodel.UserPostsViewModel
import com.example.fragrecview.ui.userposts.viewmodel.UserPostsViewModelFactory

class UserPostsFragment : Fragment() {

    private lateinit var postAdapter: PostsAdapter
    private lateinit var showPostViewModel : UserPostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPostViewModel = ViewModelProvider(this@UserPostsFragment, UserPostsViewModelFactory(
            PostsRepository(GetRetrofitInstance().getRetrofitInstance(), UserDatabase.getDatabase(requireContext()).postsDao())
        )
        )[UserPostsViewModel::class.java]
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(context)
        postAdapter = PostsAdapter(requireContext()) { postId ->
            showPostViewModel.toggleFav(postId)
        }
        recyclerView.adapter = postAdapter
        showPostViewModel.observePost().observe(viewLifecycleOwner) { post ->
            postAdapter.updateData(post)
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
