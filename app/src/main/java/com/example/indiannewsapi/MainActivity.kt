package com.example.indiannewsapi

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.indiannewsapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        getNews()

    }

    fun  getNews() {
        val news=NewsService.newsInstance.getHeadline("in",1)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val new=response.body()
                new?.let {
                    adapter= NewsAdapter(this@MainActivity, it.articles)
                    binding.newsList.adapter=adapter
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Api_Call","Error in Fetching API")
            }
        })

    }
}