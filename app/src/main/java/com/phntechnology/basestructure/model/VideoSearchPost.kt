package com.phntechnology.basestructure.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoSearchPost (
    val text: String? = null,
    val safesearch: String? = null,
    val timelimit: String? = null,
    val duration: String? = null,
    val resolution: String? = null,
    val region: String? = null,

    @SerializedName("max_results")
    val maxResults: Long? = null
) : Parcelable
