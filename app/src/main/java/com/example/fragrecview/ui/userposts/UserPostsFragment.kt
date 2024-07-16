package com.example.fragrecview.ui.userposts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragrecview.R
import com.example.fragrecview.ui.userdetails.UserDetailsFragment
import com.example.fragrecview.ui.userposts.adapter.PostsAdapter
import com.example.fragrecview.ui.userposts.viewmodel.UserPostsViewModel

class UserPostsFragment : Fragment() {

    private lateinit var userPostsViewModel: UserPostsViewModel
    private lateinit var rvAdapter: PostsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewPosts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        rvAdapter = PostsAdapter()
        recyclerView.adapter = rvAdapter

        userPostsViewModel = ViewModelProvider(this)[UserPostsViewModel::class.java]

        userPostsViewModel.getPosts { posts ->
            Log.d("UserPostsFragment", "Posts received: ${posts.size}")
            rvAdapter.updateData(posts)
        }

        val goBackBtn: Button = view.findViewById(R.id.goBackButton)
        goBackBtn.setOnClickListener {
            loadFragment(UserDetailsFragment())
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
