package com.tes.android.projects.tvshowsapp.presentation.search_show_listings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.tes.android.projects.tvshowsapp.core.mapper.toShowListing
import com.tes.android.projects.tvshowsapp.data.repository.FakeRepository
import com.tes.android.projects.tvshowsapp.data.repository.getDummyResponse
import com.tes.android.projects.tvshowsapp.domain.use_case.FavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SearchShowListingsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutionRule: TestRule = InstantTaskExecutorRule()

    private lateinit var searchShowListingsViewModel: SearchShowListingsViewModel
    private lateinit var fakeRepository: FakeRepository
    private lateinit var useCase:FavoriteUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
         Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeRepository()
        useCase= FavoriteUseCase(fakeRepository)
        searchShowListingsViewModel = SearchShowListingsViewModel(fakeRepository, testDispatcher,useCase )
    }

    @Test
    fun `success data response from api`() = runTest {
        searchShowListingsViewModel.onEvent(SearchShowListingsEvent.LoadShows)
        searchShowListingsViewModel.uiState.test {
            assertEquals(awaitItem(),  SearchShowListingsState(isLoading =false)) //initialise state
            assertEquals(awaitItem(),  SearchShowListingsState(isLoading =true)) //actual loading state
            assertEquals(getDummyResponse().map { it.toShowListing() }, awaitItem().shows) //emit  data
        }
    }
    @Test
    fun `failure data response from api`()= runTest{
        fakeRepository.shouldEmitError=true
        searchShowListingsViewModel.onEvent(SearchShowListingsEvent.LoadShows)
        searchShowListingsViewModel.uiState.test {
            awaitItem() // //initialise state
            awaitItem() //actual loading state
            assertEquals("Error message", awaitItem().error)  //error case
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}


