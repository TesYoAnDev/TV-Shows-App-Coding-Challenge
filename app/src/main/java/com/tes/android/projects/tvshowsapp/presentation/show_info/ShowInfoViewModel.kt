package com.tes.android.projects.tvshowsapp.presentation.show_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tes.android.projects.tvshowsapp.domain.repository.ShowRepository
import com.tes.android.projects.tvshowsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ShowRepository,
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    var state by mutableStateOf(ShowInfoState())

    init {
        viewModelScope.launch(dispatcher) {
            val q = savedStateHandle.get<String>("name")?: return@launch
            state = state.copy(isLoading = true)

            when (val result = repository.getShowInfo(q)) {
                is Resource.Success -> {
                    state = state.copy(
                        show = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        show = null,
                        error = result.message,
                        isLoading = false
                    )
                }
                else -> Unit
            }
        }
    }
}