package com.sano.tmdbtestapp.data.pojo

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)