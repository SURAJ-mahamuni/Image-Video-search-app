package com.phntechnology.basestructure.helper

import android.content.Context
import android.util.Log
import com.example.commonutils.util.requestToCurl
import com.phntechnology.basestructure.util.Constants.api_key
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(@ApplicationContext val appContext: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // we can write log url here
        val request = chain.request().newBuilder()
        request.apply {
            addHeader("content-type", "application/json")
            addHeader("X-RapidAPI-Key", "93de3825c9msh091b653f6528bcdp1acb71jsn04f7be679d6c")
            addHeader("X-RapidAPI-Host", "google-api31.p.rapidapi.com")
        }
        Log.e("CURL_REQUEST : ", requestToCurl(request.build()))
        // add token here with the header
        return chain.proceed(request.build())
    }

}