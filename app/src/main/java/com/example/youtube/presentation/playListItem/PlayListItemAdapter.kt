package com.example.youtube.presentation.playListItem

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.ItemPlayListItemBinding

class PlayListItemAdapter : Adapter<PlayListItemAdapter.PlayListItemHolder>() {

    private val _list = mutableListOf<PlayListModel.Item>()
    private val list: List<PlayListModel.Item> get() = _list

    fun addData(playlistModelItem: List<PlayListModel.Item>) {
        _list.clear()
        _list.addAll(playlistModelItem)
        notifyItemRangeInserted(_list.size, playlistModelItem.size - _list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListItemHolder {
        return PlayListItemHolder(ItemPlayListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: PlayListItemHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlayListItemHolder(private val binding: ItemPlayListItemBinding) : ViewHolder(binding.root) {
            fun bind(playListItem:PlayListModel.Item){
                with(binding){
                    tvName.text=playListItem.snippet.title
                    Glide.with(imgVideo).load(playListItem.snippet.thumbnails.default.url).into(imgVideo)
                }
                Log.e("ololo", "bind: $playListItem", )
            }

    }
}