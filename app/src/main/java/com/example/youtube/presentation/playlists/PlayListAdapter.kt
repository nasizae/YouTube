package com.example.youtube.presentation.playlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.ItemYoutubeListBinding

class PlayListAdapter():Adapter<PlayListAdapter.PlayerListHolder>() {
    private var _list = mutableListOf<PlayListModel.Item>()
    private val list get() = _list

    fun addData(playlistModelItem: List<PlayListModel.Item>) {
        _list.clear()
        _list.addAll(playlistModelItem)
        notifyItemRangeInserted(_list.size, playlistModelItem.size - _list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListHolder {
        return PlayerListHolder(ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: PlayerListHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class PlayerListHolder( var binding: ItemYoutubeListBinding):ViewHolder(binding.root) {
        fun bind(playerListModel:PlayListModel.Item){
            with(binding){
                    name.text=playerListModel.snippet.title
                    tvCount.text=playerListModel.contentDetails.itemCount.toString()
                    Glide.with(image).load(playerListModel.snippet.thumbnails.default.url).into(image)
                }
            }
        }
    }
