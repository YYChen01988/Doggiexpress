package com.dogBreed.dogBreed.data.api

import com.dogBreed.dogBreed.data.model.Breeds
import com.dogBreed.dogBreed.data.model.DogImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedsApi {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Breeds

    @GET("breed/{dogBreed}/images/random")
    suspend fun getDogImage(
        @Path("dogBreed") dogBreed: String
    ): DogImageResponse


}
