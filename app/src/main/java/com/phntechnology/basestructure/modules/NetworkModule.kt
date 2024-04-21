package com.phntechnology.basestructure.modules

import com.phntechnology.basestructure.apis.RetrofitApi
import com.phntechnology.basestructure.helper.NullOnEmptyConverterFactory
import com.phntechnology.basestructure.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .baseUrl(Constants.BASE_URI)
    }

    @Singleton
    @Provides
    fun providesLoginAPI(
        retrofitBuilder: Retrofit.Builder,
    ): RetrofitApi {
        return retrofitBuilder.build().create(RetrofitApi::class.java)
    }

}