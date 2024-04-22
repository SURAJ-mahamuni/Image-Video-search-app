package com.phntechnology.basestructure.repos

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.phntechnology.basestructure.R
import com.phntechnology.basestructure.apis.RetrofitApi
import com.phntechnology.basestructure.helper.NetworkResult
import com.phntechnology.basestructure.model.SearchResultPost
import com.phntechnology.basestructure.model.SearchResultResponse
import com.phntechnology.basestructure.model.VideoResultResponse
import com.phntechnology.basestructure.model.VideoSearchPost
import com.phntechnology.basestructure.util.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    @ApplicationContext private val application: Context,
    private val api: RetrofitApi
) {
    private val searchResultMutableLiveData = MutableLiveData<NetworkResult<SearchResultResponse>>()

    val searchResultLiveData: LiveData<NetworkResult<SearchResultResponse>>
        get() = searchResultMutableLiveData

    private val videoResultMutableLiveData = MutableLiveData<NetworkResult<VideoResultResponse>>()

    val videoResultLiveData: LiveData<NetworkResult<VideoResultResponse>>
        get() = videoResultMutableLiveData

    suspend fun getImageSearchResult(data: SearchResultPost) {
        searchResultMutableLiveData.postValue(NetworkResult.Loading())
        if (NetworkUtils.isInternetAvailable(application)) {
            try {
                val result = api.getImageSearchResult(data)
                if (result.isSuccessful && result.body() != null) {
                    searchResultMutableLiveData.postValue(NetworkResult.Success(result.body()))
                } else if (result.errorBody() != null) {
                    searchResultMutableLiveData.postValue(NetworkResult.Error(R.string.error_body))
                } else {
                    searchResultMutableLiveData.postValue(NetworkResult.Error(R.string.error_body))
                }
                Log.e("error", result.code().toString())
            } catch (e: Exception) {
                Log.e("exception", e.message ?: "")
                searchResultMutableLiveData.postValue(NetworkResult.Error(R.string.some_exception_happen))
            }
        } else {
            searchResultMutableLiveData.postValue(NetworkResult.Error(R.string.no_internet_connection))
        }
    }

    suspend fun getVideoSearchResult(data: VideoSearchPost) {
        videoResultMutableLiveData.postValue(NetworkResult.Loading())
        if (NetworkUtils.isInternetAvailable(application)) {
            try {
                val result = api.getVideoSearchResult(data)
                if (result.isSuccessful && result.body() != null) {
                    videoResultMutableLiveData.postValue(NetworkResult.Success(result.body()))
                } else if (result.errorBody() != null) {
                    videoResultMutableLiveData.postValue(NetworkResult.Error(R.string.error_body))
                } else {
                    videoResultMutableLiveData.postValue(NetworkResult.Error(R.string.error_body))
                }
                Log.e("error", result.code().toString())
            } catch (e: Exception) {
                Log.e("exception", e.message ?: "")
                videoResultMutableLiveData.postValue(NetworkResult.Error(R.string.some_exception_happen))
            }
        } else {
            videoResultMutableLiveData.postValue(NetworkResult.Error(R.string.no_internet_connection))
        }
    }
}