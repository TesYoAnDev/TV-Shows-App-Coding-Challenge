package com.tes.android.projects.tvshowsapp.presentation.favorite_shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.android.projects.tvshowsapp.domain.repository.ShowRepository
import com.tes.android.projects.tvshowsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteShowsViewModel @Inject constructor(
    private val repository: ShowRepository,
    private val dispatcher: CoroutineDispatcher

) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteShowsUiState())

    val uiState: StateFlow<FavoriteShowsUiState> = _uiState.asStateFlow()

    fun onEvent(event: FavoriteShowsEvent) {
        when (event) {

            is FavoriteShowsEvent.OnDeleteSelected -> {
              //  _uiState.value = _uiState.value.copy(id = event.id)
                _uiState.update { it.copy(id=event.id) }

                viewModelScope.launch {
                    deleteFavorite()
                }
            }
            is FavoriteShowsEvent.LoadFavoriteShows-> getFavoriteShowListings()
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
