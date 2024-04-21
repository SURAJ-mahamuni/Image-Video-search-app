package com.phntechnology.basestructure.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.commonutils.util.backPressedHandle
import com.example.commonutils.util.hideView
import com.example.commonutils.util.showView
import com.example.commonutils.util.toastMsg
import com.phntechnology.basestructure.R
import com.phntechnology.basestructure.SearchViewModel
import com.phntechnology.basestructure.adapters.GenericAdapter
import com.phntechnology.basestructure.databinding.FragmentSearchBinding
import com.phntechnology.basestructure.databinding.ImageListItemBinding
import com.phntechnology.basestructure.helper.NetworkResult
import com.phntechnology.basestructure.model.Result
import com.phntechnology.basestructure.model.SearchResultPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var _adapter: GenericAdapter<Result, ImageListItemBinding>? = null
    private val adapter get() = _adapter!!

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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
        viewModel.searchLiveData.observe(viewLifecycleOwner) {
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
            ImageListItemBinding::inflate,
            onBind = { itemData, itemBinding, position, listSize ->
                itemBinding.apply {
                    imageTitle.text = itemData.title
                    width.text = getString(
                        R.string.width_height,
                        itemData.width.toString(),
                        itemData.height.toString()
                    )
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
                viewModel.getImageSearchResult(
                    data = SearchResultPost(
                        text = query,
                        safesearch = "off",
                        region = "wt-wt",
                        color = "",
                        size = "",
                        typeImage = "",
                        layout = "",
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