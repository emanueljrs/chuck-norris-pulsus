package com.emanuel.pulsusnorris.data.model.remote

import com.emanuel.pulsusnorris.data.model.local.Joke
import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)

fun JokeResponse.toJokeModel() = Joke(
    iconUrl = iconUrl,
    id = id,
    url = url,
    value = value
)
