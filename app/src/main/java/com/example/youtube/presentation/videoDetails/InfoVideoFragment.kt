package com.example.youtube.presentation.videoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.youtube.R
import com.example.youtube.data.model.PlayListModel
import com.example.youtube.databinding.FragmentInfoVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoVideoFragment : Fragment() {

    private lateinit var binding: FragmentInfoVideoBinding
    private val infoVideoViewModel :InfoVideoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentInfoVideoBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            initListeners()
        initLiveData()
        getVideo()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnDownload.setOnClickListener {
            val alertDialog= AlertDialog.Builder(requireContext())
            alertDialog.setView(R.layout.download_dialog).show()
        }

    }
    private fun initLiveData() {
        infoVideoViewModel.video.observe(viewLifecycleOwner) { item ->
            setView(item.items)
        }

        infoVideoViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading)
                binding.loading.visibility = View.VISIBLE
            else
                binding.loading.visibility = View.GONE
        }

        infoVideoViewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
    private fun setView(infoVideo: List<PlayListModel.Item>) {
        binding.tvTitle.text = infoVideo.first().snippet.title
        binding.tvDesc.text = infoVideo.first().snippet.description
        Glide.with(binding.imgVideo).load(infoVideo.first().snippet.thumbnails.standard.url).into(binding.imgVideo)

    }


    private fun initView(videoId: String) {
        infoVideoViewModel.getVideo(videoId)
    }

    private fun getVideo() {
        setFragmentResultListener("key2") { _, bundle ->
            bundle.getSerializable("k3")
                ?.let { item ->
                    val _item = item as PlayListModel.Item
                    initView(_item.contentDetails.videoId)
                }
        }
    }


}