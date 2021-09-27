package com.dogBreed.dogBreed.injection

import com.dogBreed.dogBreed.data.api.BreedsApi
import com.dogBreed.dogBreed.repository.BreedsRepository
import com.dogBreed.dogBreed.viewmodel.BreedsViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val breedsModule = module {
    factory {
        BreedsViewModel(
            breedsRepository = get()
        )
    }
    factory {
        val retrofit: Retrofit = get()
        retrofit.create(BreedsApi::class.java)
    }
    factory {
        BreedsRepository(get(), get())
    }
}
