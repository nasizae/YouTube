package com.example.youtube.presentation.playListItem.pagingLoadState

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.R
import com.example.youtube.databinding.ItemProgressbarBinding

class PagingLoadStateAdapter:LoadStateAdapter<PagingLoadStateHolder>() {
    override fun onBindViewHolder(holder: PagingLoadStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): PagingLoadStateHolder {
        return PagingLoadStateHolder.create(parent=parent)
    }
}
