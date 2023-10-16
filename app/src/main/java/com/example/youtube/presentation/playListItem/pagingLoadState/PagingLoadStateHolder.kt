package com.example.youtube.presentation.playListItem.pagingLoadState

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.R
import com.example.youtube.databinding.ItemProgressbarBinding

class PagingLoadStateHolder( binding: ItemProgressbarBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        loadState.endOfPaginationReached
    }

    companion object {
        fun create(parent: ViewGroup): PagingLoadStateHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_progressbar,
                parent,
                false
            )
            val binding = ItemProgressbarBinding.bind(view)
            return PagingLoadStateHolder(binding)
        }
    }
}