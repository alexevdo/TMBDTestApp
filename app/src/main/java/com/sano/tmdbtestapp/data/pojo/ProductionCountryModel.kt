package com.sano.tmdbtestapp.data.pojo

import com.google.gson.annotations.SerializedName

data class ProductionCountryModel(
    @SerializedName("iso_3166_1")
    val iso31661: String? = null,
    @SerializedName("name")
    val name: String? = null
)
