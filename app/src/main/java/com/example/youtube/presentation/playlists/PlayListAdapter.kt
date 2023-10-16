package com.example.youtube.presentation.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.ItemYoutubeListBinding

class PlayListAdapter(
    diffUtilCallback: DiffUtil.ItemCallback<PlayListModel.Item>,
    private val onClick: (playlistItem: PlayListModel.Item) -> Unit,
    private val resourcesProvider: PlayListFragment
) :
    PagingDataAdapter<PlayListModel.Item, PlayListAdapter.PlayerListHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListHolder {
        return PlayerListHolder(
            ItemYoutubeListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: PlayerListHolder, position: Int) {
        val newPosition = getItem(position)
        newPosition?.let {
            holder.bind(it)
        }
    }

    inner class PlayerListHolder(var binding: ItemYoutubeListBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(playerListModel: PlayListModel.Item) {
            with(binding) {
                name.text = playerListModel.snippet.title
                tvCount.text = playerListModel.contentDetails.itemCount.toString() + " video series"
                Glide.with(image).load(playerListModel.snippet.thumbnails.default.url).into(image)
                itemView.setOnClickListener { onClick(playerListModel) }
            }
        }
    }
}
