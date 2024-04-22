package com.phntechnology.basestructure.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.commonutils.util.backPressedHandle
import com.example.commonutils.util.calculateTimeDuration
import com.example.commonutils.util.hideView
import com.example.commonutils.util.showView
import com.example.commonutils.util.toastMsg
import com.phntechnology.basestructure.R
import com.phntechnology.basestructure.SearchViewModel
import com.phntechnology.basestructure.VideoSearchViewModel
import com.phntechnology.basestructure.adapters.GenericAdapter
import com.phntechnology.basestructure.databinding.FragmentVideoSearchBinding
import com.phntechnology.basestructure.databinding.ImageListItemBinding
import com.phntechnology.basestructure.databinding.VideoSearchResulrItemBinding
import com.phntechnology.basestructure.fileDownloader.FileDownloader
import com.phntechnology.basestructure.helper.NetworkResult
import com.phntechnology.basestructure.model.Result
import com.phntechnology.basestructure.model.SearchResultPost
import com.phntechnology.basestructure.model.VideoResult
import com.phntechnology.basestructure.model.VideoSearchPost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VideoSearchFragment : Fragment() {
    private var _binding: FragmentVideoSearchBinding? = null

    private val binding get() = _binding!!

    private var _adapter: GenericAdapter<VideoResult, VideoSearchResulrItemBinding>? = null

    private val adapter get() = _adapter!!

    @Inject
    lateinit var fileDownloader: FileDownloader

    private val viewModel by viewModels<VideoSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getVideoSearchResult(
            data = VideoSearchPost(
                text = "Krishna bhajans",
                safesearch = "off",
                timelimit = "",
                duration = "",
                resolution = "",
                region = "wt-wt",
                maxResults = 100
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVideoSearchBinding.inflate(layoutInflater, container, false)
        backPressedHandle {
            requireActivity().finishAffinity()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeListener()

        initializeAdapter()

        observables()

    }

    private fun observables() {
        viewModel.videoResultLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    binding.progressBar.hideView()
                    toastMsg(getString(it.message ?: R.string.some_exception_happen))
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.showView()
                }

                is NetworkResult.Success -> {
                    binding.progressBar.hideView()
                    adapter.setData(ArrayList(it.data?.result))
                }
            }
        }
    }

    private fun initializeAdapter() {
        _adapter = GenericAdapter(
            VideoSearchResulrItemBinding::inflate,
            onBind = { itemData, itemBinding, position, listSize ->
                itemBinding.apply {

                    Glide.with(requireContext()).load(itemData.images?.large).into(videoImg)
                    videoTitle.text = itemData.title
                    uploader.text = itemData.uploader
                    views.text =
                        getString(R.string.views, itemData.statistics?.viewCount.toString())
                    posted.text = calculateTimeDuration(itemData.published ?: "")

                }
            })
        binding.searchResultRv.adapter = adapter
    }

    private fun initializeListener() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                viewModel.getVideoSearchResult(
                    data = VideoSearchPost(
                        text = query,
                        safesearch = "off",
                        timelimit = "",
                        duration = "",
                        resolution = "",
                        region = "wt-wt",
                        maxResults = 100
                    )
                )
                return false
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}