package com.emanuel.pulsusnorris.data.network

import com.emanuel.pulsusnorris.data.model.remote.JokeResponse
import com.emanuel.pulsusnorris.data.model.remote.JokeSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    @GET("random")
    suspend fun getRandomJokes(@Query("category") category: String?): Response<JokeResponse>

    @GET("categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("search")
    suspend fun searchJoke(@Query("query") query: String): Response<JokeSearchResponse>
}