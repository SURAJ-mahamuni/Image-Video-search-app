package com.phntechnology.basestructure.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultPost(
    val text: String? = "",
    val safesearch: String? = "",
    val region: String? = "",
    val color: String? = "",
    val size: String? = "",

    @SerializedName("type_image")
    val typeImage: String? = "",

    val layout: String? = "",

    @SerializedName("max_results")
    val maxResults: Long? = 0
) : Parcelable
