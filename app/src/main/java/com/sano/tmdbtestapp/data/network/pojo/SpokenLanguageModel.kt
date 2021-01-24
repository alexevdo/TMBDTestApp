package com.sano.tmdbtestapp.data.network.pojo

import com.google.gson.annotations.SerializedName

data class SpokenLanguageModel(
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
)
