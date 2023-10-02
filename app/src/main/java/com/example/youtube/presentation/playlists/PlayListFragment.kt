package com.example.youtube.presentation.playlists

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.utils.Status
import com.example.youtube.databinding.FragmentPlayListBinding
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository

class PlayListFragment : Fragment() {
    private lateinit var binding: FragmentPlayListBinding
    val playerListViewModel = PlayListVIewModel(Repository(RetrofitClient().createApiService()))
    private val adapter = PlayListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlayListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()

    }

    private fun initLiveData() {
        playerListViewModel.getPlaylists().observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.let { adapter.addData(it.items) }
                    binding.rvYoutubeList.adapter = adapter
                    playerListViewModel.loading.value = false
                }

                Status.ERROR -> {
                    if (resource.data == null) {
                        Toast.makeText(requireContext(), "данные не пришли", Toast.LENGTH_SHORT)
                            .show()
                    }
                    playerListViewModel.loading.value = false
                }

                Status.LOADING -> {
                    playerListViewModel.loading.value = true
                }
            }
        }

        playerListViewModel.loading.observe(requireActivity()) {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }
    }
}

