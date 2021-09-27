package com.dogBreed.dogBreed.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogBreed.core.network.Resource
import com.dogBreed.core.network.Status
import com.dogBreed.dogBreed.data.model.Breeds
import com.dogBreed.dogBreed.repository.BreedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedsViewModel(
    private val breedsRepository: BreedsRepository,
) : ViewModel() {
    private val allCountries: MutableLiveData<Resource<Breeds>> =
        MutableLiveData<Resource<Breeds>>()

    fun fetchAllBreeds() {
        viewModelScope.launch {
            allCountries.postValue(Resource.loading(data = null))
            allCountries.postValue(breedsRepository.getAllBreeds())
        }
    }

    fun getAllBreeds() = allCountries

    fun getAllBreedNames(breeds: Breeds): List<String> {
        val breedList = mutableListOf<String>()
        breeds.message.map {
            breedList.add(it.key)
        }
        return breedList.sortedBy { it }
    }


    private val dogImageResponse: MutableLiveData<Resource<List<String>>> =
        MutableLiveData<Resource<List<String>>>()

    fun fetchTenDogImages(selectedBreed: String) {
        val images = arrayListOf<String>()
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                coroutineScope {
                    for (i in 1..11) {
                        launch {
                            val response =
                                breedsRepository.getDogImage(selectedBreed)
                            if (response.status == Status.SUCCESS) {
                                response.data?.let { images.add(it.message) }
                            }
                        }

                    }
                }
                dogImageResponse.postValue(Resource.loading(data = null))
                dogImageResponse.postValue(Resource.success(images))
            }
        }
    }

    fun getTenDogsImages() = dogImageResponse

}
