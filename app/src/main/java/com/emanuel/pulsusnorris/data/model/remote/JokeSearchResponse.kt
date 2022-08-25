package com.emanuel.pulsusnorris.data.model.remote

data class JokeSearchResponse(
    val total: Int,
    val result: List<JokeResponse>
)