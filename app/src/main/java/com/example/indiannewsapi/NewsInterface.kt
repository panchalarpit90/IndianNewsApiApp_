package com.example.indiannewsapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL="https://newsapi.org/"
const val API_KEY="87851f7a44a64782969cf6a749adf00f"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country:String,@Query("page") page:Int): Call<News>

}

object NewsService{
    val newsInstance:NewsInterface
    init {
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }
}