package com.example.randomusersapi.presentation.userlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.randomusersapi.databinding.UserListItemBinding
import com.example.randomusersapi.domain.User

class UserListViewHolder(
    private val binding: UserListItemBinding,
    private val onItemClickListener: (User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.tvUserName.text = user.firstName
        binding.root.setOnClickListener {
            onItemClickListener.invoke(user)
        }
    }
}