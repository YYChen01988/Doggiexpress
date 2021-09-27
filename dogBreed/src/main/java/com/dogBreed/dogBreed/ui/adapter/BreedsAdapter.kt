package com.dogBreed.dogBreed.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dogBreed.databinding.ListItemBreedBinding
import java.util.*

class BreedsAdapter(private val context: Context, private val breeds: List<String>) :
    RecyclerView.Adapter<BreedsAdapter.CountriesAdapterViewHolder>() {

    var breedClicked: ((String) -> Unit)? = null

    private var dataList = emptyList<String>()

    internal fun setDataList(dataList: List<String>) {
        this.dataList = dataList
    }

    class CountriesAdapterViewHolder(val listItemBreedBinding: ListItemBreedBinding) :
        RecyclerView.ViewHolder(listItemBreedBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesAdapterViewHolder =
        CountriesAdapterViewHolder(
            ListItemBreedBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CountriesAdapterViewHolder, position: Int) {
        val breed = breeds[position]
        holder.listItemBreedBinding.apply {
            if (breeds.size <= 11) {
                Glide.with(context).load(breed).into(dogImage)
            } else {
                breedName.text =
                    breed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
                breedCard.setOnClickListener {
                    breedClicked?.invoke(breed)
                }
            }
        }
    }

    override fun getItemCount(): Int = breeds.count()
}
