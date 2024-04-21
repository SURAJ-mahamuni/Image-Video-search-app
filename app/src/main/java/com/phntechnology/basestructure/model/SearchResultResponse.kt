package com.phntechnology.basestructure.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultResponse(
    val result: List<Result>? = emptyList()
) : Parcelable

@Parcelize
data class Result(
    val title: String? = "",
    val image: String? = "",
    val thumbnail: String? = "",
    val url: String? = "",
    val height: Long? = 0,
    val width: Long? = 0,
    val source: String? = ""
) : Parcelable