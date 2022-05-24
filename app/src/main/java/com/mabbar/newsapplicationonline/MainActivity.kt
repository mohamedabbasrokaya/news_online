package com.mabbar.newsapplicationonline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.mabbar.newsapplicationonline.api.ApiManager
import com.mabbar.newsapplicationonline.model.SourcesItem
import com.mabbar.newsapplicationonline.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tablayout:TabLayout
    lateinit var progress_bar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tablayout=findViewById(R.id.tablayout)
        getNewsSources()
    }

     fun getNewsSources() {
      ApiManager.getApis()
          .getSources(constant.apikey)
          .enqueue(object :Callback<SourcesResponse>{
              override fun onResponse(
                  call: Call<SourcesResponse>,
                  response: Response<SourcesResponse>
              ) {
                  progress_bar.isVisible=false
                  addSourcesToTablayout(response.body()?.sources)
              }

              override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                  Log.e("error",t.localizedMessage)
                                }

              })
    }

    fun addSourcesToTablayout(sources: List<SourcesItem?>?) {
        sources?.forEach {
            val tab=tablayout.newTab()
            tab.setText(it?.name)
            tablayout.addTab(tab)
        }

    }
}