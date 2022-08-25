package com.emanuel.pulsusnorris.data.repository

import com.emanuel.pulsusnorris.data.model.remote.JokeResponse
import com.emanuel.pulsusnorris.data.model.remote.JokeSearchResponse
import retrofit2.Response

interface ChuckNorrisRepository {

    suspend fun getRandomJokes(category: String?): Response<JokeResponse>

    suspend fun getCategoriesJokes(): Response<List<String>>

    suspend fun searchJoke(text: String): Response<JokeSearchResponse>
}