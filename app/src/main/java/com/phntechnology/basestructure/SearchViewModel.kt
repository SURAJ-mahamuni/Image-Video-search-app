package com.phntechnology.basestructure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phntechnology.basestructure.helper.NetworkResult
import com.phntechnology.basestructure.model.SearchResultPost
import com.phntechnology.basestructure.model.SearchResultResponse
import com.phntechnology.basestructure.repos.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {
    val searchLiveData: LiveData<NetworkResult<SearchResultResponse>> =
        searchRepository.searchResultLiveData

    fun getImageSearchResult(data: SearchResultPost) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getImageSearchResult(data)
        }

    }
}