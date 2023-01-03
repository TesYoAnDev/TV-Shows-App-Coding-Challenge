package com.tes.android.projects.tvshowsapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.android.projects.tvshowsapp.domain.repository.ShowRepository
import com.tes.android.projects.tvshowsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ShowRepository,
    private val dispatcher: CoroutineDispatcher

) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUiState())

    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun onEvent(event: FavoriteShowListingEvent) {
        when (event) {

            is FavoriteShowListingEvent.OnDeleteSelected -> {
                _uiState.value = _uiState.value.copy(id = event.id)
                viewModelScope.launch {
                    deleteFavorite()
                }
            }
            is FavoriteShowListingEvent.LoadFavoriteShows-> getFavoriteShowListings()
        }
    }

    private fun deleteFavorite(
        id: Int = _uiState.value.id
    ) {
        viewModelScope.launch(dispatcher) {
            repository.deleteFavoriteById(id)
            getFavoriteShowListings()
        }

    }

    private fun getFavoriteShowListings(
    ) {
        viewModelScope.launch(dispatcher) {
            repository.getFavorites()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                // copy of  current state so that we can change the listing
                                _uiState.value = _uiState.value.copy(favoriteShows = listings)
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}
