package com.tes.android.projects.tvshowsapp.presentation.favorite

import com.tes.android.projects.tvshowsapp.domain.model.ShowListing

data class FavoriteUiState(
    val favoriteShows: List<ShowListing> = emptyList(),
    val id:Int=0,
    val isLoading:Boolean =false,

)
