package com.example.youtube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemYoutubeListBinding

class PlayerListAdapter(private var playerList:ArrayList<PlayerListModel>):Adapter<PlayerListAdapter.PlayerListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListHolder {
        return PlayerListHolder(ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=playerList.size

    override fun onBindViewHolder(holder: PlayerListHolder, position: Int) {
        holder.bind(playerList.get(position))

    }

    inner class PlayerListHolder( var binding: ItemYoutubeListBinding):ViewHolder(binding.root) {
        fun bind(playerListModel: PlayerListModel){
            with(binding){
                name.text=playerListModel.name
                tvCount.text=playerListModel.count.toString()
                Glide.with(image).load(playerListModel.image).into(image)
            }
        }
    }
}