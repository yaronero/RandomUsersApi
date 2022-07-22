package com.example.randomusersapi.presentation.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.randomusersapi.databinding.UserListItemBinding
import com.example.randomusersapi.domain.User

class UserListAdapter(
    private val onItemClickListener: (User) -> Unit
) : ListAdapter<User, UserListViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserListViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}