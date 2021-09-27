package com.dogBreed.dogBreed.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dogBreed.core.network.Status
import com.dogBreed.databinding.FragmentBreedsBinding
import com.dogBreed.dogBreed.ui.activity.BreedDetailActivity
import com.dogBreed.dogBreed.ui.adapter.BreedsAdapter
import com.dogBreed.dogBreed.viewmodel.BreedsViewModel
import org.koin.android.ext.android.inject

class BreedsFragment : Fragment() {

    private val breedsViewModel: BreedsViewModel by inject()

    private lateinit var breedsAdapter: BreedsAdapter

    private lateinit var fragmentBreedsBinding: FragmentBreedsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBreedsBinding = FragmentBreedsBinding.inflate(inflater, container, false)
        return fragmentBreedsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        breedsViewModel.getAllBreeds().observe(
            viewLifecycleOwner,
            {
                when (it.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        val allBreeds =
                            it.data?.let { breeds -> breedsViewModel.getAllBreedNames(breeds) }
                        allBreeds?.let { breedNames -> loadBreedsInRecyclerView(breedNames) }
                    }
                    Status.ERROR -> {
                    }
                }
            }
        )
        breedsViewModel.fetchAllBreeds()
    }

    private fun loadBreedsInRecyclerView(breedNames: List<String>) {
        fragmentBreedsBinding.rvBreeds.apply {
            breedsAdapter = BreedsAdapter(context, breedNames)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = breedsAdapter
            breedsAdapter.setDataList(breedNames)
        }
        breedsAdapter.breedClicked = {

            val intent = Intent(activity, BreedDetailActivity::class.java)
            intent.putExtra("NAME", it)
            startActivity(intent)

        }
    }
}
