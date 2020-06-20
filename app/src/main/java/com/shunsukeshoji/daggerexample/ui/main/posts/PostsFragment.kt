package com.shunsukeshoji.daggerexample.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.shunsukeshoji.daggerexample.R
import com.shunsukeshoji.daggerexample.ui.main.Status
import com.shunsukeshoji.daggerexample.viewmodels.ViewModelProviderFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    @Inject
    lateinit var postItemFactory: PostItem.Factory

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var groupAdapter: GroupAdapter<GroupieViewHolder>

    private val viewModel: PostsViewModel by lazy {
        providerFactory.create(PostsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.posts.removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner) {

            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    it.data?.let { list ->
                        groupAdapter.update(
                            list.map { post ->
                                postItemFactory.create(post)
                            }
                        )
                    }
                }

                Status.ERROR -> {

                }
            }
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}