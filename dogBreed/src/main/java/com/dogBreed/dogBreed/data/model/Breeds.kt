package com.dogBreed.dogBreed.data.model

import com.google.gson.annotations.SerializedName

data class Breeds(
    @SerializedName("message")
    val message: HashMap<String, List<String>>
)