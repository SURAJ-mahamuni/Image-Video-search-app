package com.phntechnology.basestructure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phntechnology.basestructure.helper.NetworkResult
import com.phntechnology.basestructure.model.VideoResultResponse
import com.phntechnology.basestructure.model.VideoSearchPost
import com.phntechnology.basestructure.repos.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoSearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {
    val videoResultLiveData: LiveData<NetworkResult<VideoResultResponse>> =
        searchRepository.videoResultLiveData

    fun getVideoSearchResult(data: VideoSearchPost) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getVideoSearchResult(data)
        }

    }
}