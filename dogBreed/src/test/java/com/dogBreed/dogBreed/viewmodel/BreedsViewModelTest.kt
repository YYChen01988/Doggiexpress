package com.dogBreed.dogBreed.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dogBreed.core.network.Resource
import com.dogBreed.dogBreed.data.model.Breeds
import com.dogBreed.dogBreed.repository.BreedsRepository
import com.dogBreed.dogBreed.utils.TestCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BreedsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var breedsRepository: BreedsRepository

    @Mock
    private lateinit var apiBreedsObserver: Observer<Resource<Breeds>>

    private lateinit var viewModel: BreedsViewModel

    private var breedResponse = Breeds(
        hashMapOf(
            "pug" to emptyList(),
            "poodle" to emptyList(),
            "australian" to listOf("shepherd"),
            "eskimo" to emptyList(),
            "corgi" to emptyList(),
            "redbone" to emptyList(),
            )
    )

    @Before
    fun setUp() {
        viewModel = BreedsViewModel(breedsRepository)
    }

    @Test
    fun `given server response 200 when fetch should return success`() {
        startFetchingBreedsResult(Resource.success(data = breedResponse))
    }

    @Test
    fun `given server response error when fetch should return error`() {
        val errorMessage = "Dummy Error Message"
        startFetchingBreedsResult(
            Resource.error(
                data = null,
                RuntimeException(errorMessage).toString()
            )
        )
    }

    @Test
    fun `breed response return to a breed list sorted alphabetically`() {
        val result = viewModel.getAllBreedNames(breedResponse)
        assertEquals("australian",result.first())
        assertEquals("redbone",result.last())

    }

    private fun startFetchingBreedsResult(data: Resource<Breeds>) {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(data).`when`(breedsRepository).getAllBreeds()
            viewModel.fetchAllBreeds()
            viewModel.getAllBreeds().observeForever(apiBreedsObserver)

            Mockito.verify(breedsRepository).getAllBreeds()
            Mockito.verify(apiBreedsObserver).onChanged(data)
            viewModel.getAllBreeds().removeObserver(apiBreedsObserver)
        }
    }
}

