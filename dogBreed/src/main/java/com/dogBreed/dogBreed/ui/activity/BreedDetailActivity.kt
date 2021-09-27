package com.dogBreed.dogBreed.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.dogBreed.core.network.Status
import com.dogBreed.databinding.ActivityBreedDetailBinding
import com.dogBreed.dogBreed.ui.adapter.BreedsAdapter
import com.dogBreed.dogBreed.viewmodel.BreedsViewModel
import org.koin.android.ext.android.inject
import java.util.*

class BreedDetailActivity : AppCompatActivity() {

    private lateinit var activityBreedDetailBinding: ActivityBreedDetailBinding

    private lateinit var breedsAdapter: BreedsAdapter

    private val breedsViewModel: BreedsViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBreedDetailBinding = ActivityBreedDetailBinding.inflate(layoutInflater)
        val view = activityBreedDetailBinding.root
        setContentView(view)

        val name = intent.getStringExtra("NAME")


        breedsViewModel.getTenDogsImages().observe(
            this,
            {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        it.data?.let { images -> loadDogImagesInRecyclerView(images) }
                        Glide.with(this).load(it.data?.last())
                            .into(activityBreedDetailBinding.dogImage)
                        activityBreedDetailBinding.selectedBreedName.title =
                            name?.replaceFirstChar { breedName ->
                                if (breedName.isLowerCase()) breedName.titlecase(
                                    Locale.getDefault()
                                ) else breedName.toString()
                            }
                    }
                    Status.ERROR -> {
                    }
                }
            }
        )
        name?.let { breedsViewModel.fetchTenDogImages(it) }
    }

    private fun loadDogImagesInRecyclerView(dogImages: List<String>) {
        activityBreedDetailBinding.rvImage.apply {
            breedsAdapter = BreedsAdapter(context, dogImages)
            layoutManager = GridLayoutManager(context, 2)
            adapter = breedsAdapter
            breedsAdapter.setDataList(dogImages)
        }
    }

}