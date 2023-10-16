package com.example.youtube.presentation.playlists

import IsOnline
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.FragmentPlayListBinding
import com.example.youtube.presentation.playListItem.pagingLoadState.PagingLoadStateAdapter
import com.example.youtube.presentation.utils.UserComporator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListFragment : Fragment() {
    private lateinit var binding: FragmentPlayListBinding
    private val playerListViewModel : PlayListVIewModel by viewModel()
    private val adapter = PlayListAdapter(UserComporator,this::onClickItem, resourcesProvider = this)
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
        initListeners()
        initView()
    }

    private fun initView() {
        binding.rvYoutubeList.adapter=adapter
    }

    private fun initListeners() {
        IsOnline(requireContext()).observe(viewLifecycleOwner){isConect->
            if (!isConect){
                binding.noInternet.root.visibility=View.VISIBLE
            }
            binding.noInternet.btnTryAgain.setOnClickListener {
                if (isConect){
                    binding.noInternet.root.visibility=View.GONE
                    initLiveData()
                }
            }
        }
    }
    private fun initLiveData() {
        playerListViewModel.getPagingPlaylists().observe(viewLifecycleOwner){

            binding.rvYoutubeList.adapter=adapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(),
                footer = PagingLoadStateAdapter()
            )

            playerListViewModel.viewModelScope.launch (Dispatchers.IO){
                adapter.submitData(
                    lifecycle=lifecycle,it
                )
            }

        }
        playerListViewModel.loading.observe(viewLifecycleOwner){loading->
            if (loading){
                binding.loading.visibility=View.VISIBLE
            }
            else{
                binding.loading.visibility=View.GONE
            }
        }
        playerListViewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }
    private fun onClickItem(playListModel: PlayListModel.Item){
        setFragmentResult("key1", bundleOf("key" to playListModel))
        findNavController().navigate(R.id.playListItemFragment)
        Log.e("ololo", "onClickItem: $playListModel", )
    }
}

