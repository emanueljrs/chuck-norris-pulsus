package com.emanuel.pulsusnorris.data.datasource

import com.emanuel.pulsusnorris.data.model.remote.JokeResponse
import com.emanuel.pulsusnorris.data.model.remote.JokeSearchResponse
import com.emanuel.pulsusnorris.data.network.RetrofitInstance
import com.emanuel.pulsusnorris.data.repository.ChuckNorrisRepository
import retrofit2.Response

class ChuckNorrisDataSource : ChuckNorrisRepository {

    private val service = RetrofitInstance.retrofit

    override suspend fun getRandomJokes(category: String?): Response<JokeResponse> {
        return service.getRandomJokes(category)
    }

    override suspend fun getCategoriesJokes(): Response<List<String>> {
        return service.getCategories()
    }

    override suspend fun searchJoke(text: String): Response<JokeSearchResponse> {
        return service.searchJoke(text)
    }
}