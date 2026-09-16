package com.dip.aiassistant.data.models

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("setup")
    val setup: String? = null,
    @SerializedName("punchline")
    val punchline: String? = null,
    @SerializedName("joke")
    val joke: String? = null,
    @SerializedName("error")
    val error: Boolean? = false
)
