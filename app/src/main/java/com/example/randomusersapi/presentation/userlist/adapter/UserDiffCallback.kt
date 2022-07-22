package com.example.randomusersapi.presentation.userlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.randomusersapi.domain.User

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}