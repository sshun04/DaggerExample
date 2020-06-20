package com.shunsukeshoji.daggerexample.ui.main.posts

import com.shunsukeshoji.daggerexample.R
import com.shunsukeshoji.daggerexample.models.Post
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_post.view.*

class PostItem @AssistedInject constructor(
    @Assisted private val post: Post
) : Item() {
    override fun getLayout(): Int = R.layout.item_post

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.title.text = post.title
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(post: Post): PostItem
    }
}