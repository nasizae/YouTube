package com.example.youtube.presentation.playListItem

import IsOnline
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.core.di.viewModelModule
import com.example.youtube.core.network.RemoteDataSource
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.FragmentPlayListItemBinding
import com.slottica.reviewfueatures.youtube57_3.domain.repository.Repository
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListItemFragment : Fragment() {

    private lateinit var binding: FragmentPlayListItemBinding
    private val playerListItemViewModel: PlayListItemViewModel by viewModel()
    override fun getContext(): Context? {
        return super.getContext()
    }
    private var adapter = PlayListItemAdapter(this::onClickItem)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlayListItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initGetResultListener()
        initLiveData()
    }

    private fun initListeners() {
        IsOnline(requireContext()).observe(viewLifecycleOwner) { isConect ->
            if (!isConect) {
                binding.noInternet.root.visibility = View.VISIBLE
            }
            binding.noInternet.btnTryAgain.setOnClickListener {
                if (isConect) {
                    binding.noInternet.root.visibility = View.GONE
                    initLiveData()
                }
            }
        }
    }

    private fun initLiveData() {
        playerListItemViewModel.playlistsItem.observe(viewLifecycleOwner) {
            getData(it.items)
        }
        playerListItemViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }
        playerListItemViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(playListItem: List<PlayListModel.Item>) {
        adapter.addData(playListItem)
        binding.rvVideo.adapter = adapter
    }

    private fun initGetResultListener() {
        setFragmentResultListener("key1") { _, bundle ->
            bundle.getSerializable("key")
                ?.let { item ->
                    val _item = item as PlayListModel.Item
                    CordinatorLayout(_item)
                    initView(_item.id)
                    Log.e("ololo", "initGetResultListener: $_item")
                }
        }

    }

    private fun initView(id: String) {
        playerListItemViewModel.getPlaylists(id)
    }

    @SuppressLint("SetTextI18n")
    private fun CordinatorLayout(playListItem: PlayListModel.Item) {
        with(binding) {
            tvTitle.text = playListItem.snippet.title
            tvDesc.text = playListItem.snippet.description
            tvCountVideo.text = playListItem.contentDetails.itemCount.toString() + "video series"
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

    private fun onClickItem(playlistItem: PlayListModel.Item) {
        setFragmentResult(
            "key2",
            bundleOf("k3" to playlistItem)
        )
        findNavController().navigate(R.id.infoVideoFragment)
    }

}