package com.example.youtube.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.youtube.data.model.PlayListModel

object UserComporator:DiffUtil.ItemCallback<PlayListModel.Item>()  {
        override fun areItemsTheSame(oldItem: PlayListModel.Item, newItem: PlayListModel.Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayListModel.Item, newItem: PlayListModel.Item): Boolean {
            return oldItem == newItem
        }
    }

