package com.dogBreed.application

import android.app.Application
import com.dogBreed.BuildConfig
import com.dogBreed.core.injections.networkModule
import com.dogBreed.dogBreed.injection.breedsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DogBreedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            androidContext(this@DogBreedApp)
            modules(listOf(networkModule, breedsModule))
        }
    }
}
