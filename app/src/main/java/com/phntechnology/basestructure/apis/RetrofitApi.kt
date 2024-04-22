package com.phntechnology.basestructure.apis

import com.phntechnology.basestructure.model.DemoModel
import com.phntechnology.basestructure.model.SearchResultPost
import com.phntechnology.basestructure.model.SearchResultResponse
import com.phntechnology.basestructure.model.VideoResultResponse
import com.phntechnology.basestructure.model.VideoSearchPost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {

    @POST("imagesearch")
    suspend fun getImageSearchResult(@Body data: SearchResultPost): Response<SearchResultResponse>
    @POST("videosearch")
    suspend fun getVideoSearchResult(@Body data: VideoSearchPost): Response<VideoResultResponse>

}