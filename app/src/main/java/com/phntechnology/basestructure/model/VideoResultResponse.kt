package com.phntechnology.basestructure.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoResultResponse(
    val result: List<VideoResult>? = null
) : Parcelable

@Parcelize
data class VideoResult(
    val content: String? = null,
    val description: String? = null,
    val duration: String? = null,

    @SerializedName("embed_html")
    val embedHTML: String? = null,

    @SerializedName("embed_url")
    val embedURL: String? = null,

    @SerializedName("image_token")
    val imageToken: String? = null,

    val images: Images? = null,
    val provider: String? = null,
    val published: String? = null,
    val publisher: String? = null,
    val statistics: Statistics? = null,
    val title: String? = null,
    val uploader: String? = null
) : Parcelable

@Parcelize
data class Images(
    val large: String? = null,
    val medium: String? = null,
    val motion: String? = null,
    val small: String? = null
) : Parcelable

@Parcelize
data class Statistics(
    val viewCount: Long? = null
) : Parcelable