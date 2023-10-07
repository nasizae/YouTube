package com.example.youtube.presentation.playListItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube.R
import com.example.youtube.databinding.FragmentPlayListItemBinding

class PlayListItemFragment : Fragment() {

    private lateinit var binding: FragmentPlayListItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentPlayListItemBinding.inflate(inflater, container, false)
        return binding.root
    }
}