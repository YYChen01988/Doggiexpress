package com.dogBreed.dogBreed.repository

import com.dogBreed.core.network.ResponseHandler
import com.dogBreed.dogBreed.data.api.BreedsApi

class BreedsRepository(
    private val breedsApi: BreedsApi,
    private val responseHandler: ResponseHandler
) {
    suspend fun getAllBreeds() = try {
        val response = breedsApi.getAllBreeds()
        responseHandler.handleSuccess(data = response)
    } catch (e: Exception) {
        responseHandler.handleException(e)
    }

    suspend fun getDogImage(dogBreed: String) = try {
        val response = breedsApi.getDogImage(dogBreed)
        responseHandler.handleSuccess(data = response)
    } catch (e: Exception) {
        responseHandler.handleException(e)
    }

}
