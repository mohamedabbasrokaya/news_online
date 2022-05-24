package com.mabbar.newsapplicationonline.api

import com.mabbar.newsapplicationonline.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface webservices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query ("apiKey")apiKey:String
    ):Call<SourcesResponse>
}