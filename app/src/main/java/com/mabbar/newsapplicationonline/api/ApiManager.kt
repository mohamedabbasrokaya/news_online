package com.mabbar.newsapplicationonline.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level


class ApiManager {
    companion object{
        private var retrofit:Retrofit?=null;
        private fun getInstance():Retrofit{
            if (retrofit==null){
                val logging = HttpLoggingInterceptor { message -> Log.e("api", message) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit=Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!;
        }
        fun getApis():webservices{
            return getInstance().create(webservices::class.java)

        }

    }
}