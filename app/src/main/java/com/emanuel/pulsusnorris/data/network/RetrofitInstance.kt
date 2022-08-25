package com.emanuel.pulsusnorris.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.chucknorris.io/jokes/")
            .build()
    }

    val retrofit: ChuckNorrisApi = getRetrofit().create(ChuckNorrisApi::class.java)
}